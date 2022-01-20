/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author vasco
 */
@Entity
@Table(name="events_table")
public class Event{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer eventID;
    
    @Column
    private String eventName, eventDate, eventDescription;
    
    @Column
    private Timestamp eventDateWithTimestamp;
    
    @Column
    private Float amount;
    
    public Event(){}
    
    public Event(String name, String description, String date, Timestamp dateT, Float price){
        this.eventName = name;
        this.eventDate = date;
        this.eventDescription = description;
        this.eventDateWithTimestamp = dateT;
        this.amount = price;
    }
    
    public void setEventName(String name){
        this.eventName = name;
    }
    
    public void setEventDate(String date){
        this.eventDate = date;
    }
    
    public void setEventDescription(String description){
        this.eventDescription = description;
    }
    
    public void setEventDateWithTimestamp(Timestamp date){
        this.eventDateWithTimestamp= date;
    }
    
    public void setPriceToPay(Float price) {
        this.amount = price;
    }
    
    public Integer getEventID(){
        return eventID;
    }
    
    public String getEventName(){
        return eventName;
    }
    
    public String getEventDate(){
        return eventDate;
    }
    
    public String getEventDescription(){
        return eventDescription;
    }
    
    public Timestamp getEventDateWithTimestamp(){
        return eventDateWithTimestamp;
    }
    
    public Float getEventPriceToPay(){
        return amount;
    }
}



