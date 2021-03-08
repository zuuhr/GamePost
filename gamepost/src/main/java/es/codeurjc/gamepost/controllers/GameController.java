package es.codeurjc.gamepost.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.Forum;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.enums.Developer;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.objects.enums.Publisher;
import es.codeurjc.gamepost.repositories.DescriptionRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.enums.DeveloperRepository;
import es.codeurjc.gamepost.repositories.enums.GenreRepository;
import es.codeurjc.gamepost.repositories.enums.PlatformRepository;
import es.codeurjc.gamepost.repositories.enums.PublisherRepository;

@Controller
public class GameController {
    
    @Autowired
    GameRepository gameRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    GenreRepository genreRepository;
    
    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @PostConstruct
    public void init(){
        Description d = new Description(
            "Legend of Zelda: Breath of the wild",
            new ArrayList<Genre>(Arrays.asList(genreRepository.save(new Genre("Adventure")))),
            1,
            null,
            new ArrayList<Platform>(Arrays.asList(platformRepository.save(new Platform("Switch")))),
            developerRepository.save(new Developer("Nintendo")),
            publisherRepository.save(new Publisher("Nintendo")),
            "El jugador controla a Link, que despierta en un mundo postapocalíptico después de estar cien años durmiendo para derrotar a Ganon y salvar al reino de Hyrule."
        );

        Game g = new Game("jaja", d);
        gameRepository.save(g);
    }

    //TODO: Associate this method with the form in the web
    @RequestMapping("/submit/Game")
    public String submitGame(Model model, @RequestParam String cover, 
        @RequestParam String name, @RequestParam List<Genre> genre, @RequestParam int numPlayers, 
        @RequestParam Date publishedDate, @RequestParam List<Platform> platform, 
        @RequestParam Developer developer, @RequestParam Publisher publisher, @RequestParam String synopsis){
        
        Description d = descriptionRepository.save(new Description(
            name, genre, numPlayers, publishedDate, platform, developer, publisher, synopsis
        ));

        gameRepository.save(new Game(cover, d));

        return "index"; //TODO: Return a meaningfull html
    }

    @GetMapping("/game/{id}")
    public String getGame(Model model, @PathVariable int id){
        Optional<Game> game = gameRepository.findById(id);

        if(game.isPresent()){
            model.addAttribute("game", game.get());
            return "game";
        }else{
            return "game";
        }
    }
}
