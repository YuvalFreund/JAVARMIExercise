package interfaces;

import java.util.ArrayList;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Flight;

public interface IFlightClient extends Remote {
	
	public void receiveListOfFlights(ArrayList<Flight> flights)throws RemoteException;
	
	public void receiveUpdatedFlight(Flight flight, boolean deleted)throws RemoteException;

	public void flightUploadFromClient(Flight flight, boolean deleted)throws RemoteException;

	public void logoutClient()throws RemoteException;

}
