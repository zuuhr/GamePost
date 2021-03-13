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

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.Forum;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.objects.enums.Developer;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.objects.enums.Publisher;
import es.codeurjc.gamepost.repositories.DescriptionRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.UserRepository;
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

        @Autowired
        ForumEntryRepository forumEntryRepository;

        @Autowired
        UserRepository userRepository;

        @PostConstruct
        public void init() {
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
                gameRepository.save(g);

                // g.getForum().addForumEntry(fe);
        }

        // TODO: Associate this method with the form in the web
        @RequestMapping("/submit/Game")
        public String submitGame(Model model, @RequestParam String cover, @RequestParam String name,
                        @RequestParam List<Genre> genre, @RequestParam int numPlayers, @RequestParam Date publishedDate,
                        @RequestParam List<Platform> platform, @RequestParam String developer,
                        @RequestParam String publisher, @RequestParam String synopsis) {

                Description d = descriptionRepository.save(new Description(name, genre, numPlayers, publishedDate,
                                platform, developer, publisher, synopsis));

                gameRepository.save(new Game(cover, d));

                return "submitgame";
        }

    @GetMapping("/game/{id}")
    public String getGame(Model model, @PathVariable int id) {
        Optional<Game> game = gameRepository.findById(id);
        List<User> users = userRepository.findAll();

        if (game.isPresent()) {
            model.addAttribute("game", game.get());
            model.addAttribute("description", game.get().getDescription());
            ForumEntry fe = new ForumEntry("Hello world", users.get(0), game.get(),
                    new Content("my firsst content", "url here"));
            Comment comment = new Comment(users.get(0), new Content(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    "url here"), 0);
            Comment comment2 = new Comment(users.get(0), new Content(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    "url here"), 0);

            fe.addComment(comment);
            fe.addComment(comment2);
            forumEntryRepository.save(fe);
            List<ForumEntry> posts = forumEntryRepository.findAll();
            model.addAttribute("posts", posts);
            //TODO: model.addAttribute("user", user);
            return "game";
        } else {
            return "game";
        }
    }
}
