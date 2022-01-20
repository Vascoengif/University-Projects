
package com.mycompany.services;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import net.minidev.json.JSONObject;


@RestController
@RequestMapping(path="/event")
public class EventController {
    
    private final EventService eventService;
    
    @Autowired
    public EventController(EventService eventService)
    {
        this.eventService = eventService;
    }
    
    @GetMapping(
                path="/getEventsOnADate")
    public List<Event> getEvents(@PathParam("date") String date)
    {
        
        return eventService.getEventsOnADate(date);
    }
    
    @GetMapping(
                path="/getListOfParticipants")
    public List<Participant> getListOfParticipants(@PathParam("eventName") String eventName)
    {
        
        return eventService.ListOfParticipants(eventName);
    }
    
    @GetMapping(
                path="/getClassificationAtAPoint")
    public List<Participant> getClassificationAtAPoint(@PathParam("eventName") String eventName, @PathParam("locationID") String locationID)
    {
        
        return eventService.Classification(eventName, locationID);
    }
    
    
    @PostMapping(
		path = "/newEvent",
		consumes = "application/json",
                produces = "application/json")
    public String addRegist(@RequestBody JSONObject message)
    {
        String name = (String) message.get("name");
        String type = (String) message.get("type");
        String date = (String) message.get("date");
        String dateWithTimestamp = (String) message.get("dateWithTimestamp");
        
        Timestamp timestamp = Timestamp.valueOf(dateWithTimestamp);
       
        return eventService.addRegist(name, type, date, timestamp);
    }
    
}
