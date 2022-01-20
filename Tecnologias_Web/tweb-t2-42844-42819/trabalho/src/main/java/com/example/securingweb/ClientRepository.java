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
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    List<Client> findByFirstName(String firstName);
    
    Client findOneByUsername(String username);
    
    Client findOneByParticipantID(Integer participantID);
    
}





