/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author vasco
 */
@Controller
public class RoleController {
    
    @Autowired
    private ClientRepository clientRepository;
    
    
    @GetMapping("/role-controller")
    public String listProduct(Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   
        Client client = clientRepository.findOneByUsername(auth.getName());
        
        switch(client.getRole()){
            
            case("USER"):
                System.out.println("HELLOOOOO USER");
                return "redirect:/MainPage_Athlete";
                
            case("ADMIN"):
                System.out.println("HELLOOOOO ADMIN"); 
                return "redirect:/MainPage_Admin";
        }
        
        return "";
    }
    
}