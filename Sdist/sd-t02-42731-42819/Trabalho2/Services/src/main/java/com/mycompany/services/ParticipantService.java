
package com.mycompany.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
public class ParticipantService {
    
    private Participant participant;
    private List<Participant> listOfParticipants;
    private Event event;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    
    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, EventRepository eventRepository){
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }
    
    @GetMapping
    public String howMany(String eventName, String point){
        
        event = eventRepository.findByEventName(eventName);
        
        if(event != null){
            //evento existe
            Integer eventID = event.getEventID();
            Integer fullNumberOfParticipants = 0;
            
            if(point.equals("start")){
                
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "start");
                fullNumberOfParticipants = listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P1");
                fullNumberOfParticipants += listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P2");
                fullNumberOfParticipants += listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P3");
                fullNumberOfParticipants += listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "finish");
                fullNumberOfParticipants += listOfParticipants.size();
                
                return fullNumberOfParticipants.toString();
                
            }
            else if(point.equals("P1")){
                
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P1");
                fullNumberOfParticipants = listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P2");
                fullNumberOfParticipants += listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P3");
                fullNumberOfParticipants += listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "finish");
                fullNumberOfParticipants += listOfParticipants.size();
                
                return fullNumberOfParticipants.toString();
            }
            else if(point.equals("P2")){
            
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P2");
                fullNumberOfParticipants = listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P3");
                fullNumberOfParticipants += listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "finish");
                fullNumberOfParticipants += listOfParticipants.size();
                
                return fullNumberOfParticipants.toString();
            }
            else if(point.equals("P3")){
                
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "P3");
                fullNumberOfParticipants = listOfParticipants.size();
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "finish");
                fullNumberOfParticipants += listOfParticipants.size();
                
                return fullNumberOfParticipants.toString();
            }
            else if(point.equals("finish")){
                
                listOfParticipants = participantRepository.findAllByEventIDAndLocationID(eventID, "finish");
                fullNumberOfParticipants = listOfParticipants.size();
                
                return fullNumberOfParticipants.toString();
            }
            else{
                return "O Ponto Intermédio inserido não é válido.";
            }
        }
        else{
            return "O evento não existe.";
        }
    }
    
    
    @PostMapping
    public String addNewParticipant(JSONObject message){
        
        String eventName = (String) message.get("eventName");
        String participantName = (String) message.get("participantName");
        String genre = (String) message.get("genre");
        String echelon = (String) message.get("echelon");
        
        event = eventRepository.findByEventName(eventName);
        
        if(event == null){ 
            
            return "ERRO! O evento referido não existe."; 
        }
 
        Integer eventIDInteger = event.getEventID();

        String eventID = Integer.toString(eventIDInteger);

        listOfParticipants = participantRepository.findAllByEventID(eventIDInteger);

        String dorsal = Integer.toString(listOfParticipants.size() + 1);

        String chipID = "E".concat(eventID).concat("P").concat(dorsal);

        System.out.println("teste aqui: " + chipID);
        
        Integer dorsalInt = Integer.parseInt(dorsal);

        participant = new Participant(chipID, participantName, genre, echelon, dorsalInt, eventIDInteger);
        
        participantRepository.save(participant);
        
        return "Novo Participante registado com sucesso com o dorsal nº".concat(dorsal);
        
    }
    
    @PostMapping
    public String addNewSensorLecture(String chipID, String locationID, Timestamp dateWithTimestamp){
        
        participant = participantRepository.findByChipID(chipID);
        event = eventRepository.findByEventID(participant.getEventID());
        
        Timestamp eventStartTimestamp = event.getDateWithTimstamp();
        
        if(participant != null){
            //existe esse chipID
            
            if(dateWithTimestamp.after(eventStartTimestamp)){
                //o tempo enviado é depois do início do evento
                
                Timestamp participantStartTimestamp = participant.getstartTimestamp();
                Timestamp participantLastTimestamp = participant.getLastTimestamp();
                String participantLocationID = participant.getLocationID();

                if(!(locationID.equals("start")) && participantLocationID == null){

                    return "O participante ainda não passou Sensores anteriores, não foi possível registar o tempo.";
                }
                else if(locationID.equals("start") && participantLocationID == null && participantStartTimestamp == null){
                    //inicial
                    //registar o start
                    participant.setLocationID(locationID);
                    participant.setStartTimestamp(dateWithTimestamp);
                    participant.setLastTimestamp(dateWithTimestamp);
                    participant.setStarted(Boolean.TRUE);

                    participantRepository.save(participant);

                    return "Registo de passagem realizado com sucesso.";
                }
                else if(((locationID.equals("P1") && participantLocationID.equals("start"))
                        || (locationID.equals("P2") && participantLocationID.equals("P1"))
                        || (locationID.equals("P3") && participantLocationID.equals("P2"))
                        || (locationID.equals("finish") && participantLocationID.equals("P3")))
                        && dateWithTimestamp.after(participantLastTimestamp)){
                    //ta tudo okay relativamente a locationID ser depois do que já está registado e o tempo de prova também é depois do ultimo registado.
                    //registar

                    participant.setLocationID(locationID);
                    participant.setLastTimestamp(dateWithTimestamp);

                    long diff = dateWithTimestamp.getTime()-participantStartTimestamp.getTime();

                    participant.setTimeOfRun(diff);
                    
                    if(locationID.equals("P1")){
                        participant.setTimeAtP1(diff);
                    }
                    else if(locationID.equals("P2")){
                        participant.setTimeAtP2(diff);
                    }
                    else if(locationID.equals("P3")){
                        participant.setTimeAtP3(diff);                    
                    }            
                                
                    participantRepository.save(participant);

                    return "Registo de passagem realizado com sucesso.";
                }
                else{
                    return "O tempo enviado não é válido ou este não é o próximo Sensor que o atleta tem de passar";
                } 
            }
            else{
                return "O evento ainda não começou.";
            }
        }
        return "Não existe nenhum participante com esse chipID.";
    }
   
    
}








