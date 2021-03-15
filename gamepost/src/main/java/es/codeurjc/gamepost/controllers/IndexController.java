package es.codeurjc.gamepost.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.gamepost.objects.*;
import es.codeurjc.gamepost.objects.enums.*;
import es.codeurjc.gamepost.repositories.*;
import es.codeurjc.gamepost.repositories.enums.*;

@Controller
public class IndexController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ForumEntryRepository forumEntryRepository;

    @Autowired
    CustomListRepository customListRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    NotificationRepository notificationRepository;
    
    @GetMapping("/")
    public String enlace(Model model){
        List<Game> games = gameRepository.findAll();
        Optional<User> user = userRepository.findByName("Mariam");
        //TODO: model.addAttribute("user", user);
        model.addAttribute("games", games);
        if(user.isPresent()){
            List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get()); 
            model.addAttribute("list", customLists);
            model.addAttribute("user", user.get());
        }

        //Show forum entries
        model.addAttribute("latestForumEntries", forumEntryRepository.findAll(Sort.by("lastUpdatedOn")));

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

    @GetMapping("/profile")
    public String profile(Model model){
        Optional<User> user = userRepository.findByName("Mariam");
        if(user.isPresent()){
            model.addAttribute("user", user.get());
        }
        //TODO: model.addAttribute("user", user);
        return "profile";
    }

    @PostConstruct
    public void init(){
        genreRepository.save(new Genre("Adventure"));
        genreRepository.save(new Genre("RPG"));

        platformRepository.save(new Platform("Switch"));

        User user = userRepository.save(new User("Mariam", "password"));
        userRepository.save(new User("Julen", "wordpass"));

        Description d = new Description("Legend of Zelda: Breath of the wild",
                                new ArrayList<Genre>(Arrays.asList(genreRepository.save(new Genre("Adventure")),
                                                genreRepository.save(new Genre("RPG")))),
                                1, new Date(),
                                new ArrayList<Platform>(Arrays.asList(platformRepository.save(new Platform("Switch")))),
                                "Nintendo", "Nintendo",
                                "El jugador controla a Link, que despierta en un mundo postapocalíptico después de estar cien años durmiendo para derrotar a Ganon y salvar al reino de Hyrule.");

        Game g = new Game(
                        "https://eplakaty.pl/img/towary/1/2017_04/pp34131-the-legend-of-zelda-breath-of-the-wild-plakat-z-gry-jpg.jpg",
                        d);

        ForumEntry fe = new ForumEntry("Hello world", userRepository.findAll().get(0), g,
                        new Content("my firsst content", "url here"));

        Comment comment = new Comment(userRepository.findAll().get(0), 0, 
                    new Content(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                        "url here")
                    );

        Comment comment2 = new Comment(userRepository.findAll().get(1), 0, 
                    new Content(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                        "url here")
                    );

        fe.addComment(comment);
        fe.addComment(comment2);
        g.getForum().addForumEntry(fe);
        gameRepository.save(g);
        
        //contentRepository.save(new Content("Vaya juegazo", null));
        //contentRepository.save(new Content("No me gusta", null));
        //contentRepository.save(new Content("Me encanta", null));
        //contentRepository.save(new Content("Wow amazing", null));
        //contentRepository.save(new Content("suka blyat", null));
        //contentRepository.save(new Content("loooool goty", null));
        //contentRepository.save(new Content("omg", null));

        //notificationRepository.save(new Notification("Welcome!", "localhost:8080/index"));
        //notificationRepository.save(new Notification("Hello!", "localhost:8080/index"));

        //CustomList<ListElement> customList = new CustomList<ListElement>("My Games");
        //customList.addElement((ListElement) g);
        //user.addMyList(customList);
        //customListRepository.save(customList);
    }
}
