/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

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
@Table(name="participants_table")
public class Participant {
        
    @Id
    private String chipID;
    
    private String username;
    private Integer eventID, dorsal;
    
    private String firstName, lastName, locationID, gender, echelon;
    private Long timeAtP1, timeAtP2, timeAtP3, timeOfRun;
    private Timestamp startTimestamp, lastTimestamp;
    
    public Participant(){}
    
    public Participant(String chipID, String username, String firstName, String lastName, Integer eventID, Integer dorsal, String gender, String echelon){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventID = eventID;
        this.dorsal = dorsal;
        this.gender = gender;
        this.echelon = echelon;
        this.chipID = "E".concat(eventID.toString()).concat("A").concat(dorsal.toString());
    }
    
    public void setDorsal(Integer dorsal){
        this.dorsal = dorsal;
    }
    
    public void setLocationID(String locationID){
        this.locationID = locationID;            
    }
    
    public void setEventID(Integer eventID){
        this.eventID = eventID;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setTimeAtP1(Long time){
        this.timeAtP1 = time;
    }
    
    public void setTimeAtP2(Long time){
        this.timeAtP2 = time;
    }
    
    public void setTimeAtP3(Long time){
        this.timeAtP3 = time;
    }
    
    public void setTimeOfRun(Long time){
        this.timeOfRun = time;
    }
    
    public void setStartTimestamp(Timestamp time){
        this.startTimestamp = time;
    }
    
    public void setLastTimestamp(Timestamp time){
        this.lastTimestamp = time;
    }
    
    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getChipID(String chipID){
        return chipID;
    }
        
    public Integer getDorsal(){
        return dorsal;
    }
    
    public Integer getEventID(){
        return eventID;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
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
    
    public Long getTimeOfRun(){
        return timeOfRun;
    }
    
    public Timestamp getStartTimestamp(){
        return startTimestamp;
    }
    
    public Timestamp getLastTimestamp(){
        return lastTimestamp;
    }
    
    public String getLocationID(){
        return locationID;
    }

	public String getGender() {
		return gender;
	}

	public String getEchelon() {
		return echelon;
	}
    
   
}