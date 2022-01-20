/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

import java.util.List;
import javax.persistence.*;
/**
/**
 *
 * @author vasco
 */
@Entity
@Table(name="clients_table")
public class Client {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer participantID;
    
    @Column
    private String firstName, lastName, mail, username, password, role, gender, echelon;
    
    public Client(){}
    
    public Client(String firstName, String lastName, String mail, String username, String password, String role, String gender, String echelon){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.role = role;
        this.echelon = echelon;
        this.gender = gender;
    }
    
    public Integer getId(){
        return participantID;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getMail(){
        return mail;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRole(){
        return role; 
    }
    
    public String getGender(){
        return gender; 
    }
    
    public String getEchelon(){
        return echelon; 
    }
    
    public void setFirstName(String value){
        this.firstName = value;
    }
    
    public void setLastName(String value){
        this.lastName = value;
    }
    
    public void setMail(String value){
        this.mail = value;
    }
    
    public void setUsername(String value){
        this.username = value;
    }
    
    public void setPassword(String value){
        this.password = value;
    }
    
    public void setRole(String value){
        this.role = value;
    }
    
    public void setGender(String value){
        this.gender = value;
    }
    
    public void setEchelon(String value){
        this.echelon = value;
    }
}









