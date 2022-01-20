
package com.mycompany.services;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author vasco
 */

@Entity
@Table(name="tableEvent")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventID;
    
    private String eventName, eventType, eventDate;
    private Timestamp dateWithTimestamp;
    
    public Event(){}
    
    public Event(String name, String type, String date, Timestamp dateWithTimestamp){
        this.eventName = name;
        this.eventType = type;
        this.eventDate = date;
        this.dateWithTimestamp = dateWithTimestamp;
    }
    
    public Integer getEventID() {
        return eventID;
    }
    
    public void setEventName(String name){
        this.eventName = name;
    }
    
    public String getEventName(){
        return eventName;
    }
    
    public void setEventType(String type){
        this.eventType = type;
    }
    
    public String getEventType(){
        return eventType;
    }
    
    public void setEventDate(String date){
        this.eventDate = date;
    }
    
    public String getEventDate(){
        return eventDate;
    }
    
    public void setDateWithTimestamp(Timestamp dateWithTimestamp){
        this.dateWithTimestamp = dateWithTimestamp;
    }
    
    public Timestamp getDateWithTimstamp(){
        return dateWithTimestamp;
    }
}
