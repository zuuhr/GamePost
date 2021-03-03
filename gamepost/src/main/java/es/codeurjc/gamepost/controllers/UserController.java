package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;
import java.util.Collection;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository users;

    @PostConstruct
    public void init(){
        users.save(new User("Mariam"));
        users.save(new User("Julen"));
    }

    @GetMapping("/")
    public Collection<User> getUsers(){
        return users.findAll();
    }
}