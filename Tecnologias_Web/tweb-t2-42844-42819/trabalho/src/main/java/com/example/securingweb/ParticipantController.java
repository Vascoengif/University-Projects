/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vasco
 */
@Controller
public class ParticipantController {
    
    private Event event;
    
    @Autowired
    private ParticipantRepository participantRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @PostMapping("/addNewTimeOfRun")
    @ResponseBody
    public JSONObject addNewTimeOfRun(@RequestParam(name="eventName", required=false, defaultValue="")String eventName,
            @RequestParam(name="dorsal", required=false, defaultValue="")Integer dorsal,
            @RequestParam(name="year", required=false, defaultValue="")Integer year,
            @RequestParam(name="month", required=false, defaultValue="")Integer month,
            @RequestParam(name="day", required=false, defaultValue="")Integer day,
            @RequestParam(name="hour", required=false, defaultValue="")Integer hour,
            @RequestParam(name="minutes", required=false, defaultValue="")Integer minutes,
            @RequestParam(name="seconds", required=false, defaultValue="")Integer seconds,
            @RequestParam(name="milisseconds", required=false, defaultValue="")Integer milisseconds,
            @RequestParam(name="location", required=false, defaultValue="")String location,
            Model model){
        
        System.out.println("value + typeof: " + year + " " + year.getClass());
        System.out.println("value + typeof: " + month + " " + month.getClass());
        System.out.println("value + typeof: " + day + " " + day.getClass());
        System.out.println("value + typeof: " + hour + " " + hour.getClass());
        System.out.println("value + typeof: " + minutes + " " + minutes.getClass());
        System.out.println("value + typeof: " + seconds + " " + seconds.getClass());
        System.out.println("value + typeof: " + milisseconds + " " + milisseconds.getClass());
        
        JSONObject json = new JSONObject();
        
        Timestamp timestamp = new Timestamp(year-1900, month-1, day, hour, minutes, seconds, milisseconds);
        
        event = eventRepository.findOneByEventName(eventName);
        
        if(event != null){
            //o evento existe, pode continuar
            Participant participant = participantRepository.findOneByEventIDAndDorsal(event.getEventID(), dorsal);
            
            if(participant != null){
                //existe um participante com este dorsal no evento
                String participantLocationID = participant.getLocationID();
                
                if(location.equals("start") && participantLocationID == null){
                    participant.setStartTimestamp(timestamp);
                    participant.setLastTimestamp(timestamp);
                    participant.setLocationID("start");
                    participantRepository.save(participant);
                    json.put("resultado", "1");
                    return json;
                }
                else if((participant.getLastTimestamp() != null) && ((location.equals("P1") && participantLocationID.equals("start"))
                        || (location.equals("P2") && participantLocationID.equals("P1"))
                        || (location.equals("P3") && participantLocationID.equals("P2"))
                        || (location.equals("finish") && participantLocationID.equals("P3")))
                        && timestamp.after(participant.getLastTimestamp())){
                
                    Long diff = timestamp.getTime()-participant.getStartTimestamp().getTime();
                    participant.setLocationID(location);
                    participant.setLastTimestamp(timestamp);
                    
                    if(location.equals("P1")){
                        participant.setTimeAtP1(diff);
                    }
                    else if(location.equals("P2")){
                        participant.setTimeAtP2(diff); 
                    }
                    else if(location.equals("P3")){
                        participant.setTimeAtP3(diff); 
                    }
                    else if(location.equals("finish")){
                        participant.setTimeOfRun(diff); 
                    } 
                    participantRepository.save(participant);
                    json.put("resultado", "1");
                    return json;
                }   
                json.put("resultado", "2");
                return json;
            }
            json.put("resultado", "3");
            return json;
        }
        json.put("resultado", "4");
        return json;
    }
    
    
    
    
    @PostMapping("/registerParticipant")
    @ResponseBody
    public JSONObject registerNewParticipant(@ModelAttribute("eventName")String eventName,
            Model model){
        
        JSONObject json = new JSONObject();
        Participant participant = new Participant();
        
        event = eventRepository.findOneByEventName(eventName);
        
        Integer eventID = event.getEventID();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   
        Client client = clientRepository.findOneByUsername(auth.getName());
        
        if(client != null && client.getRole().equals("USER")){
            
            
            participant = participantRepository.findOneByEventIDAndUsername(eventID, client.getUsername());
            
            if(participant == null){
                
                List<Participant> list = participantRepository.findAllByEventID(eventID);
            
                Integer dorsal = list.size() +1;
                
                String chipID = "E".concat(eventID.toString()).concat("A").concat(dorsal.toString());
                        
                participant = new Participant(chipID, client.getUsername(), client.getFirstName(),
                        client.getLastName(), eventID, dorsal, client.getGender(), client.getEchelon());

                participantRepository.save(participant);

                json.put("result", "ok");
                return json;
            }
            
            json.put("result", "exist");
            return json;
        }
        else if(client != null && client.getRole().equals("ADMIN")){
      
            json.put("result", "admin");
            return json;
        }
      
        json.put("result", "other");
        return json;
    }
    
    
    
    @GetMapping("/getSubscriptions")
    @ResponseBody
    public JSONObject getSubscriptions(Model model){
        
        JSONObject json = new JSONObject();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   
        Client client = clientRepository.findOneByUsername(auth.getName());
        
        List<Participant> list = participantRepository.findAllByUsername(client.getUsername());
        
        if(list.size() == 0){
            
            json.put("message", "no subscriptions");
            return json;
        }
        
        List anotherWithEventsNames = new ArrayList();
        
        Integer eventID;
        
        for(int i = 0; i < list.size(); i++){
            
            eventID = list.get(i).getEventID();
            
            event = eventRepository.findOneByEventID(eventID);
            
            anotherWithEventsNames.add(event.getEventName());
        }
        
        json.put("message", "ok");
        json.put("list", anotherWithEventsNames);
        
        return json;
        
    }
    
    
    
    @GetMapping("/getParticipantsList")
    @ResponseBody
    public JSONObject getParticipantsList(@ModelAttribute("eventName") String eventName,
            Model model){
    
        JSONObject json = new JSONObject();
        
        event = eventRepository.findOneByEventName(eventName);
        Integer eventID = event.getEventID();
        
        List<Participant> listOfParticipants = participantRepository.findAllByEventID(eventID);
       
        if(listOfParticipants.size() > 0){
            
            json.put("list", listOfParticipants);
            json.put("resultado", "ok");
            return json;
        }
        else{
            json.put("resultado", "nope");
            return json;
        }
        
    }
    
    
    
    @GetMapping("/getClassif")
    @ResponseBody
    public JSONObject getClassification(@ModelAttribute("evento") String eventName,
            @ModelAttribute("genero") String gender,
            Model model){
        
        JSONObject json = new JSONObject();
        
        
        event = eventRepository.findOneByEventName(eventName);
        Integer eventID = event.getEventID();
        List<Participant> list = participantRepository.findAllByEventIDAndLocationID(eventID, "finish");
       
        if(gender.equals("M")){
            List<Participant> maleList = new ArrayList<Participant>();
            
            if(maleList == null){
                json.put("resultado", "nope");
                return json;
            }
            
            for(int i = 0; i < list.size(); i++){
                
                if(list.get(i).getGender().equals("M")){
                    maleList.add(list.get(i));
                }
            }
            
            list = maleList;
        }
        else if(gender.equals("F")){
            List<Participant> femaleList = new ArrayList<Participant>();
            
            if(femaleList == null){
                json.put("resultado", "nope");
                return json;
            }
            
            for(int i = 0; i < list.size(); i++){
                
                if(list.get(i).getGender().equals("F")){
                    femaleList.add(list.get(i));
                }
            }
            
            list = femaleList;
        }
        
        Participant participant = new Participant();
        
        for (int i = 0; i < list.size(); i++) {

            for (int j = 0; j < list.size(); j++) {

                if ((list.get(i).getTimeOfRun()) < list.get(j).getTimeOfRun()) {

                    participant = list.get(i);

                    list.set(i, list.get(j));

                    list.set(j, participant);
                }
            }
        }
        
        for(int i = 0; i < list.size(); i ++){
            System.out.println(list.get(i).getTimeOfRun());
        }
        
        json.put("list", list);
        json.put("resultado", "ok");
        return json;
        
    }
    
    
    
}
