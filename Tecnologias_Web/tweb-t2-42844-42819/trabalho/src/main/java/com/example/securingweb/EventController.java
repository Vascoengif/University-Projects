/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vasco
 */
@Controller
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ClientRepository clientRepository;
    
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @PostMapping("/addNewEvent") 
    public String addNewEvent(@RequestParam(name="eventName", required=false, defaultValue="")String eventName,
            @RequestParam(name="eventDescription", required=false, defaultValue="")String eventDescription,
            @RequestParam(name="year", required=false, defaultValue="")Integer year,
            @RequestParam(name="month", required=false, defaultValue="")Integer month,
            @RequestParam(name="day", required=false, defaultValue="")Integer day,
            @RequestParam(name="hour", required=false, defaultValue="")Integer hour,
            @RequestParam(name="minutes", required=false, defaultValue="")Integer minutes,
            @RequestParam(name="seconds", required=false, defaultValue="")Integer seconds,
            @RequestParam(name="milisseconds", required=false, defaultValue="")Integer milisseconds,
            @RequestParam(name="amount", required=false, defaultValue="")Float amount,
            Model model){
        
        String monthS, dayS;
        
        monthS = month.toString();
        dayS = day.toString();
        
        if(month<10){
            monthS = '0'+month.toString();
        }
        if(day<10){
            dayS = '0'+day.toString();
        }
       
        String eventDate = year.toString().concat("-").concat(monthS).concat("-").concat(dayS);
        
        Integer yearFake = year-1900;
        Integer monthFake = month-1;
        
        Timestamp timestamp = new Timestamp(yearFake, monthFake, day, hour, minutes, seconds, milisseconds);
        
        System.out.println("daqui timestamp: " + timestamp);
        
        Event event = new Event(eventName, eventDescription, eventDate, timestamp, amount);
        
        eventRepository.save(event);
        
        return "redirect:/MainPage_Admin";
    }
    
    
    
    
    @PostMapping("/searchEvents")
    @ResponseBody
    public JSONObject searchEvents(@ModelAttribute("eventName") String eventName,
            @ModelAttribute("eventDate") String eventDate) throws ParseException{
        
        List<Event> list;
        List<Event> anotherList = new ArrayList<Event>();
        JSONObject json = new JSONObject();
        
        list = eventRepository.findAll();
        
        LocalDate actualDate = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if(eventName.equals("realizados") && eventDate.equals("realizados")){

            for(int i = 0; i < list.size(); i++){
                
                LocalDate dateOfEvent = LocalDate.parse(list.get(i).getEventDate(), formatter);
                
                if(dateOfEvent.isBefore(actualDate)){
                    
                    anotherList.add(list.get(i));
                }
            }
            
            json.put("list", anotherList);
            
            return json; 
        }
        else if(eventName.equals("hoje") && eventDate.equals("hoje")){

            for(int i = 0; i < list.size(); i++){
                
                LocalDate dateOfEvent = LocalDate.parse(list.get(i).getEventDate(), formatter);
                
                if(dateOfEvent.isEqual(actualDate)){
                    
                    anotherList.add(list.get(i));
                }
            }
            
            json.put("list", anotherList);
            
            return json; 
        }
        else if(eventName.equals("futuros") && eventDate.equals("futuros")){

            for(int i = 0; i < list.size(); i++){
                
                LocalDate dateOfEvent = LocalDate.parse(list.get(i).getEventDate(), formatter);
                
                if(dateOfEvent.isAfter(actualDate)){
                    
                    anotherList.add(list.get(i));
                }
            }
            
            json.put("list", anotherList);
            
            return json; 
        }

        list = eventRepository.findAllByEventNameOrEventDate(eventName, eventDate);
        
        json.put("list", list);
        
        return json;
    }
    
    
    
    
    
    
}
