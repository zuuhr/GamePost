package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

import java.util.Collection;

import javax.annotation.PostConstruct;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
        userRepository.save(new User("Mariam", "password"));
        userRepository.save(new User("Julen", "wordpass"));
    }

    /*
    @GetMapping("/users")
    public Collection<User> getUsers(){
        return users.findAll();
    }
    */

    @RequestMapping("/signIn")
    public String signIn(Model model, @RequestParam String username, @RequestParam String password){
        Optional<User> user = userRepository.findByName(username);
        if(user.isPresent()){
           //model.repeatedUser = true --> Displays a message in the Sign in page "The name is not available." 
           return "signin";
        }else{
            log.info("INFO: The name is available");
            userRepository.save(new User(username, password));
            return "index";
        }
    }
}