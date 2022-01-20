
package t1;

import java.sql.Statement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 *
 * @author vasco
 */
public class RunningEventsServer {
    
    private static String dbHost;
    private static String dbName;
    private static String dbUsername;
    private static String dbPassword;
    
    
    public static void main(String args[]){
        
        try{
            InputStream input = new FileInputStream("resources/credentials2.properties");
            Properties properties = new Properties();
            properties.load(input);
            
            dbHost = properties.getProperty("db.host");
            dbName = properties.getProperty("db.name");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
            
        }catch(IOException e){
            System.out.println("Something went wrong! It was not possible to open the credentials.properties file");
            System.exit(1);
        }
        
        if (args.length !=1) { 
	    System.out.println("You forgot to add the registry Port!");
	    System.exit(1);
	}
	
        
	try {
	    // ATENÇÃO: este porto é relativo ao binder e não ao servidor RMI
	    int regPort=Integer.parseInt(args[0]);
            PostgresConnector pc = new PostgresConnector(dbHost, dbName, dbUsername, dbPassword);   
            
            pc.connect();
            
            
            
            Statement stmt = pc.getStatement();

	    // criar um Objeto Remoto
	    RunningEvents obj = new RunningEventsImpl(stmt);
            
	    // usar o Registry local (mesma máquina) e porto regPort
	    java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);

            
	    // Bind. Args: nome do serviço e objeto remoto
	    registry.rebind("runningevents", obj);

            
	    System.out.println("RMI object bound to service in registry");

            System.out.println("servidor pronto");
	} 
	catch (Exception ex) {
	    ex.printStackTrace();
	}
        //pc.disconnect();
    }
    
}
