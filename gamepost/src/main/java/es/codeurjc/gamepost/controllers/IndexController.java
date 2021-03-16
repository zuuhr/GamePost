package es.codeurjc.gamepost.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public String enlace(Model model) {
        List<Game> games = gameRepository.findAll();
        // TODO: model.addAttribute("user", user);
        model.addAttribute("games", games);
        Optional<User> user = userRepository.findByName("Mariam");
        if (user.isPresent()) {
            List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get());
            model.addAttribute("list", customLists);
            model.addAttribute("user", user.get());
        }

        // Show forum entries
        model.addAttribute("latestposts", forumEntryRepository.findAll(Sort.by("lastUpdatedOn")));

        return "index";
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        return "signin";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Optional<User> user = userRepository.findByName("Mariam");
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        }
        // TODO: model.addAttribute("user", user);
        return "profile";
    }

    @PostConstruct
    public void init() throws ParseException {
        genreRepository.save(new Genre("Action"));
        genreRepository.save(new Genre("Adventure"));
        genreRepository.save(new Genre("Fighting"));
        genreRepository.save(new Genre("Role Playing"));
        genreRepository.save(new Genre("Racing"));
        genreRepository.save(new Genre("Simulation"));
        genreRepository.save(new Genre("Sports"));
        genreRepository.save(new Genre("Strategy"));
        genreRepository.save(new Genre("Visual Novel"));

        platformRepository.save(new Platform("Windows"));
        platformRepository.save(new Platform("Mac"));
        platformRepository.save(new Platform("Linux"));
        platformRepository.save(new Platform("Playstation 5"));
        platformRepository.save(new Platform("Playstation 4"));
        platformRepository.save(new Platform("Nintendo Switch"));
        platformRepository.save(new Platform("Xbox Series X/S"));
        platformRepository.save(new Platform("Android"));
        platformRepository.save(new Platform("IOS"));

        userRepository.save(new User("Julen", "wordpass"));
        userRepository.save(new User("Dani", "123"));
        userRepository.save(new User("Maria", "987"));
        User user = new User("Mariam", "password");

        Notification n0 = new Notification("/", "Welcome!");
        Notification n1 = new Notification("/", "Hello!");
        user.addNotification(n0);
        user.addNotification(n1);

        userRepository.save(user);

        Description description0 = new Description("Legend of Zelda: Breath of the wild",
                new ArrayList<Genre>(Arrays.asList(genreRepository.findByText("Adventure").get(),
                        genreRepository.findByText("Role Playing").get())),
                1, new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2017"),
                new ArrayList<Platform>(Arrays.asList(platformRepository.findByText("Nintendo Switch").get())),
                "Nintendo", "Nintendo",
                "El jugador controla a Link, que despierta en un mundo postapocalíptico después de estar cien años durmiendo para derrotar a Ganon y salvar al reino de Hyrule.");

        Game game0 = new Game(
                "https://eplakaty.pl/img/towary/1/2017_04/pp34131-the-legend-of-zelda-breath-of-the-wild-plakat-z-gry-jpg.jpg",
                description0);

        Description description1 = new Description("Dark Souls II: Scholar of the First Sin",
                new ArrayList<Genre>(Arrays.asList(genreRepository.findByText("Action").get(),
                        genreRepository.findByText("Role Playing").get())),
                1, new SimpleDateFormat("dd/MM/yyyy").parse("04/03/2014"),
                new ArrayList<Platform>(Arrays.asList(platformRepository.findByText("Windows").get(),
                        platformRepository.findByText("Playstation 4").get())),
                "FromSofware", "Bandai Namco Games",
                "Once upon a time, the Kingdom of Drangleic was a majestic, thriving land. However, mysterious events led to the exile of King Vendrick, and the land became desolate... ");

        Game game1 = new Game("https://g2anewsprod02storage.s3.amazonaws.com/app/uploads/2019/03/Dark-Souls-II.jpg",
                description1);

        Description description2 = new Description("Fire Emblem: Three Houses",
                new ArrayList<Genre>(Arrays.asList(genreRepository.findByText("Strategy").get(),
                        genreRepository.findByText("Role Playing").get())),
                1, new SimpleDateFormat("dd/MM/yyyy").parse("26/07/2019"),
                new ArrayList<Platform>(Arrays.asList(platformRepository.findByText("Nintendo Switch").get())),
                "Intelligent Systems", "Nintendo",
                "Here, order is maintained by the Church of Seiros, which hosts the prestigious Officer’s Academy within its headquarters. You are invited to teach one of its three mighty houses...");

        Game game2 = new Game("https://www.justpushstart.com/wp-content/uploads/2019/08/fire-emblem-three-houses-switch-cover.jpg",
                description2);

        ForumEntry fe = new ForumEntry("Hello world", userRepository.findAll().get(0), game0,
                new Content("my firsst content", "url here"));

        Comment comment = new Comment(userRepository.findAll().get(0), fe, null, new Content(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                "url here"));

        Comment comment2 = new Comment(userRepository.findAll().get(1), fe, null, new Content(
                "No estoy de acuerdo.",
                "url here"));

        fe.addComment(comment);
        fe.addComment(comment2);
        game0.getForum().addForumEntry(fe);
        gameRepository.save(game0);
        gameRepository.save(game1);
        gameRepository.save(game2);

        CustomList<ListElement> customList = new CustomList<ListElement>("My Games", user);
        customList.addElement((ListElement) game0);
        user.addMyList(customList);
        customListRepository.save(customList);

        CustomList<ListElement> customList2 = new CustomList<ListElement>("Random entries", user);
        customList2.addElement((ListElement) fe);
        user.addMyList(customList2);
        customListRepository.save(customList2);
    }
}
