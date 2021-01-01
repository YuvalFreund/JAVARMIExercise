package interfaces;

import model.Flight;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFlightServer extends Remote {

	public void login(String clientName, IFlightClient client)throws RemoteException;

	public void logout(String clientName)throws RemoteException;

	public int getPortFromServer()throws RemoteException;

	public void updateFlight(String clientName, Flight flight)throws RemoteException;

	public void deleteFlight(String clientName, Flight flight)throws RemoteException;

}
