package es.codeurjc.gamepost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.ListElement;
import es.codeurjc.gamepost.objects.Notification;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.repositories.CustomListRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;
import es.codeurjc.gamepost.repositories.enums.GenreRepository;
import es.codeurjc.gamepost.repositories.enums.PlatformRepository;

@Controller
public class InitInfoController {
    
    @Autowired
    GameRepository gameRepository;
    
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomListRepository customListRepository;
    
    /*
    @PostConstruct
    public void initUsers(){
        userRepository.save(
            new User("user", passwordEncoder.encode("pass"), "ROLE_USER")
        );

        userRepository.save(
            new User("admin", passwordEncoder.encode("adminpass"), "ROLE_ADMIN")
        );
    }
    

    @PostConstruct
    public void initDatabase() throws ParseException {
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

        userRepository.save(new User("Julen", passwordEncoder.encode("wordpass"), "ROLE_USER"));
        userRepository.save(new User("Dani", passwordEncoder.encode("123"), "ROLE_USER"));
        userRepository.save(new User("Maria", passwordEncoder.encode("987"), "ROLE_USER"));
        User user = new User("Mariam", passwordEncoder.encode("password"), "ROLE_USER");

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
    */
}
