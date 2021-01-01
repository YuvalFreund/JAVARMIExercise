import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;

public class FlightClient implements IFlightClient {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());
	String clientName;
	Registry registry;
	public IFlightServer serverStub;
	IFlightClient clientStub;
	private ArrayList<Flight> flights;
	private arrivalsDepartures arrivalsDeparturesPage;

	public FlightClient(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public void receiveListOfFlights(ArrayList<Flight> flights) {
		this.flights = flights;
		arrivalsDeparturesPage = new arrivalsDepartures(this.flights, clientStub);
		arrivalsDeparturesPage.main();
	}

	@Override
	public void receiveUpdatedFlight(Flight flight, boolean deleted) {
		arrivalsDeparturesPage.changeFlight(flight,deleted);
		logger.log(Level.INFO, "Flight updated: " + flight.toString());
	}


	@Override
	public void flightUploadFromClient(Flight flight,boolean deleted){
		System.out.println("flightUploadFromUser");
		try {
			if(deleted){
				serverStub.deleteFlight(clientName, flight);
			}else{
				serverStub.updateFlight(clientName, flight);
			}
		}catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void logoutClient(){
		try {
			serverStub.logout(clientName);
		}catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public void startup() {
	    boolean found = false;
	    while(!found){
            try {
                registry = LocateRegistry.getRegistry(2020);
                serverStub = (IFlightServer) registry.lookup("fs");
                int portNum = serverStub.getPortFromServer();
                clientStub = (IFlightClient) UnicastRemoteObject.exportObject(this, portNum);
                registry.bind(clientName, clientStub);
                logger.log(Level.INFO, "client up");
                serverStub.login(this.clientName,clientStub);
                found = true;
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

	}

	public static void main(String[] args) {
		try{
			FlightClient client = new FlightClient(UUID.randomUUID().toString());
			client.startup();
		}catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
