
package t1;

import java.util.LinkedList;

/**
 *
 * @author vasco
 */
public class Server_Answer implements java.io.Serializable{
    
    private int response = 0;
    private LinkedList <Integer> dorsal_list = new LinkedList <Integer>();
    private LinkedList <String> string_list = new LinkedList <String>();
    
    public int getResponse(){
        return response;
    }
    
    public void setResponse(int option){
        this.response = option;
    }
    
    public LinkedList<Integer> getDorsal_list(){
        return dorsal_list;
    }
    
    public void setDorsal_list(LinkedList<Integer> list){
        this.dorsal_list = list;
    }
    
    public LinkedList<String> getString_list(){
        return string_list;
    }
    
    public void setString_list(LinkedList<String> list){
        this.string_list = list;
    }
}
