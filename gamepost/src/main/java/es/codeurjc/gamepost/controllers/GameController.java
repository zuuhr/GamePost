package es.codeurjc.gamepost.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.Forum;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.repositories.DescriptionRepository;
import es.codeurjc.gamepost.repositories.GameRepository;

@Controller
public class GameController {
    
    @Autowired
    GameRepository gameRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    //TODO: Associate this method with the form in the web
    @RequestMapping("/submit/Game")
    public String submitGame(Model model, @RequestParam String cover, 
        @RequestParam String name, @RequestParam List<String> genre, @RequestParam int numPlayers, 
        @RequestParam Date publishedDate, @RequestParam List<String> platform, 
        @RequestParam String developer, @RequestParam String publisher, @RequestParam String synopsis){
        
        Description d = descriptionRepository.save(new Description(
            name, genre, numPlayers, publishedDate, platform, developer, publisher, synopsis
        ));

        gameRepository.save(new Game(cover, d));

        return "index"; //TODO: Return a meaningfull html
    }
}
