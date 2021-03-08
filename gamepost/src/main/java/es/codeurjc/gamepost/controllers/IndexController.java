package es.codeurjc.gamepost.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Controller
public class IndexController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    
    @GetMapping("/")
    public String enlace(Model model){
        
        List<Game> games = gameRepository.findAll();
        Optional<User> user = userRepository.findByName("Mariam");
        model.addAttribute("games", games);
        if(user.isPresent()){
            model.addAttribute("user", user);
        }
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

    @GetMapping("/game")
    public String game(Model model){
        return "game";
    }

    @GetMapping("/forum")
    public String forum(Model model){
        return "forum";
    }


}
