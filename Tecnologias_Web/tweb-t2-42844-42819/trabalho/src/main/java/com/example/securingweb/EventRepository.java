/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author vasco
 */
public interface EventRepository extends JpaRepository<Event, Integer>{
    
    List<Event> findAll();
    
    Event findOneByEventName(String eventName);
    
    List<Event> findAllByEventNameOrEventDate(String eventName, String eventDate);
    
    //List<Event> findAllByEventID(Integer eventID);
    
    Event findOneByEventID(Integer eventID);
}
