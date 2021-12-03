
package t1;

/**
 *
 * @author vasco
 */
public interface RunningEvents extends java.rmi.Remote {
    
    public Server_Answer registryPlayer(Client_Request user_request) throws java.rmi.RemoteException;
    
    public Server_Answer getEventsOfTheDay(Client_Request user_request) throws java.rmi.RemoteException;
    
    public Server_Answer registryEvent(Client_Request user_request) throws java.rmi.RemoteException;
    
    public Server_Answer printPlayersInOneEvent(Client_Request user_request) throws java.rmi.RemoteException;
    
    public Server_Answer registryTimeOfRace(Client_Request user_request) throws java.rmi.RemoteException;
    
    public Server_Answer printEventClassification(Client_Request user_request) throws java.rmi.RemoteException;
    
    public Server_Answer top3OfEachEvent(Client_Request user_request) throws java.rmi.RemoteException;
}

