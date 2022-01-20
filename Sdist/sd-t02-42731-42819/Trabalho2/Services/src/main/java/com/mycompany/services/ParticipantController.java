
package com.mycompany.services;

import java.sql.Timestamp;
import javax.websocket.server.PathParam;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/participants")
public class ParticipantController {
    
    
    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService)
    {
        this.participantService = participantService;
    }
    
    @GetMapping(
                path="/howMany")
    public String getHowMany(@PathParam("eventName") String eventName, @PathParam("point") String point)
    {
        return participantService.howMany(eventName, point);
    }
    
    @PostMapping(
		path = "/addNewParticipant",
		consumes = "application/json",
                produces = "application/json")
    public String addNewParticipantRegist(@RequestBody JSONObject message)
    {
        return participantService.addNewParticipant(message);
    }
    
    @PostMapping(
                path = "/addSensorLecture",
                consumes = "application/json",
                produces = "application/json")
    public String addNewSensorLecture(@RequestBody JSONObject message)
    {
        
        String chipID = (String) message.get("chipID");
        String locationID = (String) message.get("locationID");
        String dateWithTimestamp = (String) message.get("time");
        System.out.println("HEY " + dateWithTimestamp);
        Timestamp timestamp = Timestamp.valueOf(dateWithTimestamp);
        
        return participantService.addNewSensorLecture(chipID, locationID, timestamp);
    }
}
