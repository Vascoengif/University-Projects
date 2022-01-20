
package t1;

import java.util.LinkedList;

/**
 *
 * @author vasco
 */
public class Client_Request implements java.io.Serializable{
    
    private int dorsal;
    private LinkedList <String> string_list = new LinkedList <String>();
  
    
    public void setDorsal(int dorsal){
        this.dorsal = dorsal;
    }
    
    public int getDorsal(){
        return dorsal;
    }
    
    public LinkedList<String> getString_List(){
        return string_list;
    }
    
    public void setString_List(LinkedList<String> list){
        this.string_list = list;
    }
}
