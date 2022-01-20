/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vasco
 */
@Controller
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @PostMapping("/newClientRegist")
    public String newClient(
            @RequestParam(name="firstName", required=false, defaultValue="")String firstName, 
            @RequestParam(name="lastName", required=false, defaultValue="")String lastName,
            @RequestParam(name="mail", required=false, defaultValue="")String mail,
            @RequestParam(name="gender", required=false, defaultValue="")String gender,
            @RequestParam(name="echelon", required=false, defaultValue="")String echelon,
            @RequestParam(name="username", required=false, defaultValue="")String username,
            @RequestParam(name="password", required=false, defaultValue="")String password,
            @RequestParam(name="password_confirm", required=false, defaultValue="")String password_confirm,
            Model model){
        
        if(!(password.equals(password_confirm))){
            
            return "Passwords are not equal";
        }
        
        Client client = clientRepository.findOneByUsername(username);
        
        if(client == null){
            //pode registra-se, ainda não à nenhum com este username
            clientRepository.save(new Client(firstName, lastName, mail, username, passwordEncoder.encode(password), "USER", gender, echelon));
        
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("mail", mail);
            model.addAttribute("gender", gender);
            model.addAttribute("echelon", echelon);
            model.addAttribute("username", username);
            model.addAttribute("password", password);

            return "MainPage";
        }
        else{
            
            return "Username já existente";
        }  
    }
}