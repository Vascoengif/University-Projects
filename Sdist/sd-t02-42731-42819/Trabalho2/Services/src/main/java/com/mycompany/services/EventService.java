
package com.mycompany.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author vasco
 */
@Service
public class EventService {
    
    private Event event;
    private Participant participant;
    private List<Participant> listOfParticipants; 
    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;
    
    
    @Autowired
    public EventService(EventRepository eventRepository, ParticipantRepository participantRepository){
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
    }
    
    @GetMapping
    public List<Event> getEventsOnADate(String date){
        
        return eventRepository.findAllByEventDate(date);
    }
    
    @GetMapping
    public List<Participant> ListOfParticipants(String eventName){
        
        event = eventRepository.findByEventName(eventName);
        
        if(event == null){
            
            return null;
        }
        else{
            
            Integer eventID = event.getEventID();

            listOfParticipants = participantRepository.findAllByEventID(eventID);

            return listOfParticipants;
        }
    }
   
    @GetMapping
    public List<Participant> Classification(String eventName, String locationID){
        
        event = eventRepository.findByEventName(eventName);
        
        List<Participant> anotherListOfParticipants = new ArrayList<>();
        
        if(event == null){
            
            return null;
        }
        else{
           
            Integer eventID = event.getEventID();

            listOfParticipants = participantRepository.findAllByEventIDAndStarted(eventID, true);
            
            if(listOfParticipants == null){
                
                return null;
            }
            else{
                
                if(locationID.equals("P1")){
                
                    for(int i = 0; i < listOfParticipants.size(); i++){

                        if(((listOfParticipants.get(i).getLocationID()).equals("P1"))
                                || ((listOfParticipants.get(i).getLocationID()).equals("P2"))
                                || ((listOfParticipants.get(i).getLocationID()).equals("P3"))
                                || ((listOfParticipants.get(i).getLocationID()).equals("finish"))){

                            anotherListOfParticipants.add(listOfParticipants.get(i));
                        }
                    }
                    
                    if(anotherListOfParticipants == null){
                        
                        return null;
                    }

                    for (int i = 0; i < anotherListOfParticipants.size(); i++) {

                        for (int j = 0; j < anotherListOfParticipants.size(); j++) {

                            if (anotherListOfParticipants.get(i).getTimeAtP1()< anotherListOfParticipants.get(j).getTimeAtP1()) {

                                participant = anotherListOfParticipants.get(i);

                                anotherListOfParticipants.set(i, anotherListOfParticipants.get(j));

                                anotherListOfParticipants.set(j, participant);
                            }
                        }
                    }
                    
                    return anotherListOfParticipants;
                }
                else if(locationID.equals("P2")){

                    for(int i = 0; i < listOfParticipants.size(); i++){

                        if(((listOfParticipants.get(i).getLocationID()).equals("P2"))
                                || ((listOfParticipants.get(i).getLocationID()).equals("P3"))
                                || ((listOfParticipants.get(i).getLocationID()).equals("finish"))){

                            anotherListOfParticipants.add(listOfParticipants.get(i));
                        }
                    }
                    
                    if(anotherListOfParticipants == null){
                        
                        return null;
                    }

                    for (int i = 0; i < anotherListOfParticipants.size(); i++) {

                        for (int j = 0; j < anotherListOfParticipants.size(); j++) {

                            if (anotherListOfParticipants.get(i).getTimeAtP2()< anotherListOfParticipants.get(j).getTimeAtP2()) {

                                participant = anotherListOfParticipants.get(i);

                                anotherListOfParticipants.set(i, anotherListOfParticipants.get(j));

                                anotherListOfParticipants.set(j, participant);
                            }
                        }
                    }
                    
                    return anotherListOfParticipants;
                }
                else if(locationID.equals("P3")){

                    for(int i = 0; i < listOfParticipants.size(); i++){

                        if(((listOfParticipants.get(i).getLocationID()).equals("P3"))
                                || ((listOfParticipants.get(i).getLocationID()).equals("finish"))){

                            anotherListOfParticipants.add(listOfParticipants.get(i));
                        }
                    }
                    
                    if(anotherListOfParticipants == null){
                       
                        return null;
                    }

                    for (int i = 0; i < anotherListOfParticipants.size(); i++) {

                        for (int j = 0; j < anotherListOfParticipants.size(); j++) {

                            if (anotherListOfParticipants.get(i).getTimeAtP3()< anotherListOfParticipants.get(j).getTimeAtP3()) {

                                participant = anotherListOfParticipants.get(i);

                                anotherListOfParticipants.set(i, anotherListOfParticipants.get(j));

                                anotherListOfParticipants.set(j, participant);
                            }
                        }
                    }
                    return anotherListOfParticipants;
                }
                else if(locationID.equals("finish")){

                    for(int i = 0; i < listOfParticipants.size(); i++){

                        if((listOfParticipants.get(i).getLocationID()).equals("finish")){

                            anotherListOfParticipants.add(listOfParticipants.get(i));
                        }
                    }
                    
                    if(anotherListOfParticipants == null){
                        
                        return null;
                    }

                    for (int i = 0; i < anotherListOfParticipants.size(); i++) {

                        for (int j = 0; j < anotherListOfParticipants.size(); j++) {

                            if (anotherListOfParticipants.get(i).gettimeOfRun() < anotherListOfParticipants.get(j).gettimeOfRun()) {

                                participant = anotherListOfParticipants.get(i);

                                anotherListOfParticipants.set(i, anotherListOfParticipants.get(j));

                                anotherListOfParticipants.set(j, participant);
                            }
                        }
                    }
                    
                    return anotherListOfParticipants;
                }
            }
        }
        
        return null;
    }
   
    @PostMapping
    public String addRegist(String name, String type, String date, Timestamp dateWithTimestamp){
        
        event = eventRepository.findByEventName(name);
        
        if(event == null){
            
            event = new Event();
        
            event.setEventName(name);
            event.setEventType(type);
            event.setEventDate(date);
            event.setDateWithTimestamp(dateWithTimestamp);

            eventRepository.save(event);
            
            return "O evento foi registado com sucesso.";
        }
        
        return "ERRO! Já existe um evento com esse nome.";
    }
}
