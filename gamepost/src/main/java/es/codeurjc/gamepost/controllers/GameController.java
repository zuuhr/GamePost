package es.codeurjc.gamepost.controllers;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet
        .support.ServletUriComponentsBuilder.fromCurrentRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.ListElement;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.repositories.CustomListRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;


@Controller
public class GameController {

        @Autowired
        GameRepository gameRepository;

        @Autowired
        ForumEntryRepository forumEntryRepository;

        @Autowired
        CustomListRepository customListRepository;

        @Autowired
        UserRepository userRepository;


        @RequestMapping(value = "/game/submitgame", method = RequestMethod.POST)
        public String submitGame(Model model, @RequestParam String titleText,
        @RequestParam(value = "coverFile") MultipartFile coverFile, // @RequestParam String cover,
        // @RequestParam List<String> genreValues, @RequestParam List<String>
        // platformValues,
        @RequestParam String playersText, @RequestParam String developerText,
        @RequestParam String releaseText, @RequestParam String publisherText,
        @RequestParam String descriptionText) throws ParseException, IOException {

                //Description
                List<Genre> genres = new ArrayList<Genre>();
                int numPlayers = Integer.parseInt(playersText);
                List<Platform> platforms = new ArrayList<Platform>();
                Date releaseDate = new SimpleDateFormat("dd/MM/yyyy").parse(releaseText);
                Description d = new Description(titleText, genres, numPlayers, releaseDate, platforms, developerText, publisherText, descriptionText);           
                Game game = new Game("", d);

                //Cover
                game.setCoverFile(BlobProxy.generateProxy(coverFile.getInputStream(), coverFile.getSize()));
                
                URI location = fromCurrentRequest().build().toUri();
                game.setCover(location.toString());
                ResponseEntity.created(location).build();
                
                gameRepository.save(game);
                return "submitgame";
        }

        @GetMapping("/game/newgame")
        public String newGame(Model model) {
                return "submitgame";
        }

        @GetMapping("/game/{id}")
        public String getGame(Model model, @PathVariable int id) {
                Optional<Game> game = gameRepository.findById(id);

                Optional<User> user = userRepository.findByName("Mariam");
                if (user.isPresent()) {
                        List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get());
                        model.addAttribute("list", customLists);
                        model.addAttribute("user", user.get());
                        // get game lists
                        List<CustomList<ListElement>> gameLists = getUserCustomListsGame(user.get());
                        model.addAttribute("customlist", gameLists);
                }
                // Show forum entries
                model.addAttribute("latestposts", forumEntryRepository.findTop20ByOrderByLastUpdatedOnDesc());

                if (game.isPresent()) {
                        model.addAttribute("game", game.get());
                        model.addAttribute("description", game.get().getDescription());

                        List<ForumEntry> posts = forumEntryRepository.findByGame(game.get());
                        model.addAttribute("posts", posts);
                        // TODO: model.addAttribute("user", user);
                        return "game";
                } else {
                        return "redirect:/";
                }
        }

        public List<CustomList<ListElement>> getUserCustomListsGame(User user) {

                List<CustomList<ListElement>> customLists = customListRepository.findByUser(user);
                List<CustomList<ListElement>> gameLists = new LinkedList<CustomList<ListElement>>();
                for (CustomList<ListElement> customList : customLists) {
                        if (customList.getAllElements().isEmpty() || customList.getElement(0) instanceof Game) {
                                if (!customList.getName().equals("[ForumEntries]")
                                                && !customList.getName().equals("[Comments]"))
                                        gameLists.add(customList);
                        }
                }
                return gameLists;
        }
}
