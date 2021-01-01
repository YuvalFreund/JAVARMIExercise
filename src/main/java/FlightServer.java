import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;

public class FlightServer implements IFlightServer {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());
	private ArrayList<Flight> flights;
	Hashtable<String,IFlightClient> clients;
	int portNumForUsers;

	protected FlightServer() {
		super();
		portNumForUsers = 1;
		Flight flight1 = new Flight("opo","elal","Boeing 747","686","FRA","TLV","20.11.20","20.11.20","1","A","20.11.20","20.11.20","1",
				"B","BIG TEMINAL","500","17:00","18:00","b","17:00");
		Flight flight2 = new Flight("opo","LUFTHANSA","Boeing 747","456","FRA","TLV","22.11.20","22.11.20","1","A","22.11.20","22.11.20","1",
				"J","SMALL TEMINAL","300","12:00","12:00","b","18:00");
		flights = new ArrayList<>();
		flights.add(flight1);
		flights.add(flight2);
		clients = new Hashtable<>();
	}

	@Override
	public void login(String clientName, IFlightClient client) {
		clients.put(clientName,client);
		try {
			client.receiveListOfFlights(flights);
		}catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		logger.log(Level.INFO, "New client logged in: " + clientName);
	}

	@Override
	public void logout(String clientName) {
		clients.remove(clientName);
		logger.log(Level.INFO, "Client logged out: " + clientName);
	}

	@Override
	public int getPortFromServer(){
		portNumForUsers++;
		return portNumForUsers;
	}

	@Override
	public void updateFlight(String clientName, Flight flight) {
		informAllClients(flight,false);
		logger.log(Level.INFO, "Update flight: " + flight.toString());
	}

	@Override
	public void deleteFlight(String clientName, Flight flight) {
		informAllClients(flight,true);
		logger.log(Level.INFO, "Delete flight: " + flight.toString());
	}

	private void informAllClients(Flight flight, boolean deleted) {
		Set<String> clientKeys = clients.keySet();
		try {
			for (String key : clientKeys) {
				IFlightClient curr = clients.get(key);
				curr.receiveUpdatedFlight(flight, deleted);
			}
		}
		catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			FlightServer fs = new FlightServer();
			IFlightServer stub = (IFlightServer) UnicastRemoteObject.exportObject(fs, 1);
			Registry registry = LocateRegistry.createRegistry(2020);
			registry.bind("fs", stub);
			logger.info("Server is ready");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
			ex.printStackTrace();
		}
	}

}
