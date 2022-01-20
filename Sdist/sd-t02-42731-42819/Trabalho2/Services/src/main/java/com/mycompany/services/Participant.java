
package com.mycompany.services;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
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
@Table(name="ParticipantsTable")
public class Participant {
    
    @Id
    private String chipID;
    
    private Integer dorsal, eventID;
    private Boolean started;
    private String name, genre, echelon, locationID;
    private Long timeAtP1, timeAtP2, timeAtP3, timeOfRun;
    private Timestamp startTimestamp, lastTimestamp;
    
    public Participant(){}
    
    public Participant(String chipID, String name, String genre, String echelon, Integer dorsal, Integer eventID){
        this.chipID = chipID;
        this.name = name;
        this.genre = genre;
        this.echelon = echelon;
        this.dorsal = dorsal;
        this.eventID = eventID;
    }
    
    public void setChipID(String chipID){
        this.chipID = chipID;
    }
    
    public void setStarted(Boolean t){
        if(t){
            this.started = true;
        }
        else{
            this.started = false;
        }
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setGenre(String genre){
        this.genre = genre;
    }
    
    public void setEchelon(String echelon){
        this.echelon = echelon;
    }
    
    public void setLocationID(String locationID){
        this.locationID = locationID;
    }
    
    public void setDorsal(Integer dorsal){
        this.dorsal = dorsal;
    }
    
    public void setEventID(Integer eventID){
        this.eventID = eventID;
    }
    
    public void setTimeOfRun(Long timeOfRun){
        this.timeOfRun = timeOfRun;
    }
    
    public void setTimeAtP1(Long timeOfRun){
        this.timeAtP1 = timeOfRun;
    }
    
    public void setTimeAtP2(Long timeOfRun){
        this.timeAtP2 = timeOfRun;
    }
    
    public void setTimeAtP3(Long timeOfRun){
        this.timeAtP3 = timeOfRun;
    }
    
    public void setStartTimestamp(Timestamp startTimestamp){
        this.startTimestamp = startTimestamp;
    }
    
    public void setLastTimestamp(Timestamp lastTimestamp){
        this.lastTimestamp = lastTimestamp;
    }
    
    public String getChipID(){
        return chipID;
    }
    
    public String getName(){
        return name;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public String getEchelon(){
        return echelon;
    }
    
    public String getLocationID(){
        return locationID;
    }
    
    public Integer getDorsal(){
        return dorsal;
    }
    
    public Integer getEventID(){
        return eventID;
    }
    
    public Long gettimeOfRun(){
        return timeOfRun;
    }
    
    public Long getTimeAtP1(){
        return timeAtP1;
    }
    
    public Long getTimeAtP2(){
        return timeAtP2;
    }
    
    public Long getTimeAtP3(){
        return timeAtP3;
    }
    
    public Timestamp getstartTimestamp(){
        return startTimestamp;
    }
    
    public Timestamp getLastTimestamp(){
        return lastTimestamp;
    }
}
