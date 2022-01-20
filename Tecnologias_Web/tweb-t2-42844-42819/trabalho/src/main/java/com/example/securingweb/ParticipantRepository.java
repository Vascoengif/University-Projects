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
public interface ParticipantRepository extends JpaRepository<Participant, Integer>{
    
    Participant findOneByEventIDAndDorsal(Integer eventID, Integer dorsal);
    
    List<Participant> findAllByEventID(Integer eventID);
    
    Participant findOneByEventIDAndUsername(Integer eventID, String username);
    
    List<Participant> findAllByUsername(String username);
    
    List<Participant> findAllByEventIDAndLocationID(Integer eventID, String locationID);
    
    List<Participant> findAllByEventIDAndLocationIDAndGender(Integer eventID, String locationID, String Gender);
}
