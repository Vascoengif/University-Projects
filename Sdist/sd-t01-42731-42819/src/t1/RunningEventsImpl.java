
package t1;

import java.sql.Statement;
import java.sql.ResultSet;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author vasco
 */
public class RunningEventsImpl extends UnicastRemoteObject implements RunningEvents, java.io.Serializable{
    
    
    static Statement stmt ;

    
    public RunningEventsImpl(Statement stmt) throws java.rmi.RemoteException {
        this.stmt = stmt;
    }
    
    
    public Server_Answer registryPlayer(Client_Request user_request) throws RemoteException{
        
        Server_Answer new_answer = new Server_Answer();
        
        try{
            
            String genero = user_request.getString_List().get(2);
            String escalao = user_request.getString_List().get(3);
            
            boolean eventExist = false;
            
            ResultSet rs = stmt.executeQuery("SELECT event FROM events");
            
            while(rs.next()){
                if(rs.getString("event").equals(user_request.getString_List().get(0))){
                    eventExist = true;
                    break;
                }
            }
            
            if(eventExist == true){

                stmt.executeUpdate("INSERT INTO \"" + user_request.getString_List().get(0) + "\" "
                            + "(nome, genero, escalao) "
                            + "VALUES ('" + user_request.getString_List().get(1) + "', '" + genero + "', '" + escalao + "')");
                
                rs = stmt.executeQuery("SELECT dorsal FROM \"" + user_request.getString_List().get(0) + "\" ORDER BY dorsal DESC LIMIT 1");
                
                rs.next();
                
                new_answer.getDorsal_list().add(rs.getInt(1));
                
            }
            else{
                new_answer.getString_list().add("That event does not exist, you can not registry the participant");
            }
            
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        new_answer.setResponse(1);
        return new_answer;
    }
    
    
    public Server_Answer getEventsOfTheDay(Client_Request user_request) throws RemoteException{
        
        Server_Answer new_answer = new Server_Answer();
        
        try{
         
            ResultSet rs = stmt.executeQuery("SELECT event,type FROM events WHERE date='"+user_request.getString_List().get(0)+"';");
            
            while(rs.next()){
                new_answer.getString_list().add(rs.getString("event"));
                new_answer.getString_list().add(rs.getString("type"));
            }
            
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        new_answer.setResponse(2);
        return new_answer;
    }
    
    
    public Server_Answer registryEvent(Client_Request user_request) throws RemoteException{
        
        Server_Answer new_answer = new Server_Answer();
        
        try{
          
            ResultSet rs = stmt.executeQuery("SELECT event FROM events");
            
            while(rs.next()){
                if(rs.getString("event").equals(user_request.getString_List().get(0))){
                    new_answer.setResponse(3);
                    new_answer.getString_list().add("F");
                    return new_answer;
                }
            }
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS \""+user_request.getString_List().get(0)+"\"  "
                    + "(dorsal SERIAL, nome VARCHAR(50), genero VARCHAR(10), escalao VARCHAR(15), tempodeprova TIME(3))");
           
            stmt.executeUpdate("INSERT INTO events (event, type, date) "
                    + "VALUES ('"+user_request.getString_List().get(0)+"', '"+user_request.getString_List().get(1)+"' ,'"+user_request.getString_List().get(2)+"')");
            
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
       
        new_answer.setResponse(3);
        return new_answer;
    }
    
    public Server_Answer printPlayersInOneEvent(Client_Request user_request) throws RemoteException{
        
        Server_Answer new_answer = new Server_Answer();
        
        try{
            
            boolean eventExist = false;
            
            ResultSet rs = stmt.executeQuery("SELECT event FROM events");
            
            while(rs.next()){
                if(rs.getString("event").equals(user_request.getString_List().get(0))){
                    eventExist = true;
                    break;
                }
            }
            
            if(eventExist == true){
                rs = stmt.executeQuery("SELECT dorsal,nome,genero,escalao FROM \""+user_request.getString_List().get(0)+"\" ");
                
                new_answer.getString_list().add(user_request.getString_List().get(0));
                
                while(rs.next()){
                    new_answer.getDorsal_list().add(rs.getInt("dorsal"));
                    new_answer.getString_list().add(rs.getString("nome"));
                    new_answer.getString_list().add(rs.getString("genero"));
                    new_answer.getString_list().add(rs.getString("escalao"));
                }
            }
            else{
                new_answer.getString_list().add("That event does not exist");
            }
            
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        new_answer.setResponse(4);
        return new_answer;
    }
    
    public Server_Answer registryTimeOfRace(Client_Request user_request) throws RemoteException{
        
        Server_Answer new_answer = new Server_Answer();
        
        try{
            
            boolean eventExist = false;
            
            ResultSet rs = stmt.executeQuery("SELECT event FROM events");
            
            while(rs.next()){
                if(rs.getString("event").equals(user_request.getString_List().get(0))){
                    eventExist = true;
                    new_answer.getString_list().add(user_request.getString_List().get(0));
                    break;
                }
            }
            
            if(eventExist == true){
                
                rs = stmt.executeQuery("SELECT COUNT(dorsal) FROM \""+user_request.getString_List().get(0)+"\"");
                
                rs.next();
                
                if(rs.getInt(1) == 0){
                    new_answer.getString_list().add("No participants");
                }
                else if(rs.getInt(1) < user_request.getDorsal()){
                    new_answer.getString_list().add("That dorsal does not exist");
                }
                else{
                    
                    stmt.executeUpdate("UPDATE \""+user_request.getString_List().get(0)+"\" SET tempodeprova='" +user_request.getString_List().get(1)+ "' "
                            + "WHERE dorsal=" + user_request.getDorsal());
                    
                }
  
            }
            else{
                new_answer.getString_list().add("That event does not exist");
            }
            
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        new_answer.setResponse(5);
        return new_answer;
    }
    
    public Server_Answer printEventClassification(Client_Request user_request) throws RemoteException{
        
        Server_Answer new_answer = new Server_Answer();
        
        try{
            
            boolean eventExist = false;
            
            ResultSet rs = stmt.executeQuery("SELECT event FROM events");
            
            while(rs.next()){
                if(rs.getString("event").equals(user_request.getString_List().get(0))){
                    eventExist = true;
                    new_answer.getString_list().add(user_request.getString_List().get(0));
                    break;
                }
            }
            
            if(eventExist == true){
                
                new_answer.getString_list().add(user_request.getString_List().get(1));
              
                if (user_request.getString_List().get(1).equals("A")){

                    rs = stmt.executeQuery("SELECT COUNT(genero) FROM \""+user_request.getString_List().get(0)+"\"");
                    rs.next();

                    if(rs.getInt(1) == 0){
                        new_answer.getString_list().add("No participants");
                    }
                    else{
                        rs = stmt.executeQuery("SELECT dorsal,nome,tempodeprova FROM \"" + user_request.getString_List().get(0) + "\" "
                            + "WHERE tempodeprova IS NOT NULL ORDER BY tempodeprova ASC");

                        while (rs.next()){
                            new_answer.getString_list().add(rs.getString("tempodeprova"));
                            new_answer.getDorsal_list().add(rs.getInt("dorsal"));
                            new_answer.getString_list().add(rs.getString("nome"));
                        }
                    }
                }
                else{

                    rs = stmt.executeQuery("SELECT COUNT(genero) FROM \""+user_request.getString_List().get(0)+"\" "
                            + "WHERE genero='"+user_request.getString_List().get(1)+"'");
                    rs.next();

                    if(rs.getInt(1) == 0){
                        new_answer.getString_list().add("No participants");
                    }
                    else{
                        
                        rs = stmt.executeQuery("SELECT dorsal,nome,tempodeprova FROM \"" + user_request.getString_List().get(0) + "\" "
                            + "WHERE tempodeprova IS NOT NULL AND genero='" + user_request.getString_List().get(1) + "' "
                                    + "ORDER BY tempodeprova ASC");
                        
                        while (rs.next()){
                            new_answer.getString_list().add(rs.getString("tempodeprova"));
                            new_answer.getDorsal_list().add(rs.getInt("dorsal"));
                            new_answer.getString_list().add(rs.getString("nome"));
                        }
                    }
                }
            }
            else{
                new_answer.getString_list().add("That event does not exist");
            }
            
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        new_answer.setResponse(6);
        return new_answer;
    }
    
    public Server_Answer top3OfEachEvent(Client_Request user_request) throws RemoteException{
        
        Server_Answer new_answer = new Server_Answer();
        
        try{
            
            boolean eventExist = false;
            
            ResultSet rs = stmt.executeQuery("SELECT event FROM events");
            
            while(rs.next()){
                if(rs.getString("event").equals(user_request.getString_List().get(0))){
                    eventExist = true;
                    new_answer.getString_list().add(user_request.getString_List().get(0));
                    break;
                }
            }
            
            if(eventExist == true){
                
                new_answer.getString_list().add(user_request.getString_List().get(1));
                new_answer.getString_list().add(user_request.getString_List().get(2));
                
                rs = stmt.executeQuery("SELECT COUNT(genero) FROM \""+user_request.getString_List().get(0)+"\" "
                        + "WHERE genero='"+user_request.getString_List().get(1)+"' AND escalao='"+user_request.getString_List().get(2)+"'");
                rs.next();
                
                if(rs.getInt(1) == 0){
                    new_answer.getString_list().add("No participants");
                }
                else{
                    
                    rs = stmt.executeQuery("SELECT dorsal,nome,tempodeprova FROM \"" + user_request.getString_List().get(0) + "\""
                            + "WHERE tempodeprova IS NOT NULL AND genero='" + user_request.getString_List().get(1) + "' "
                                    + "AND escalao='"+ user_request.getString_List().get(2) +"' ORDER BY tempodeprova ASC LIMIT 3");
                    
                    while(rs.next()){
                        new_answer.getString_list().add(rs.getString("tempodeprova"));
                        new_answer.getDorsal_list().add(rs.getInt("dorsal"));
                        new_answer.getString_list().add(rs.getString("nome"));
                    }
                }
          
            }
            else{
                new_answer.getString_list().add("That event does not exist");
            }
            
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        new_answer.setResponse(7);
        return new_answer;
    }

}
