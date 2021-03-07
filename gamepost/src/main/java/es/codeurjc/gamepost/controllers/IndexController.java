package es.codeurjc.gamepost.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

@Controller
public class IndexController {

    boolean authenticated;
    String username;

    
    @GetMapping("/")
    public String enlace(Model model){
        // if(!authenticated){
        //     username = "Log in/Sign in";
        // } else{
        //     username = "PC de Alguien";
        // }

        // model.addAttribute("authenticated", authenticated);
        // model.addAttribute("username", username);
        return "index";
    }
    
    @GetMapping("/signin")
    public String signin(Model model){
        return "signin";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

   

}
