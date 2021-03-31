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
import es.codeurjc.gamepost.services.CustomListService;
import es.codeurjc.gamepost.services.ForumEntryService;
import es.codeurjc.gamepost.services.GameService;


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

        @Autowired
        GameService gameService;

        @Autowired
        CustomListService customListService;

        @Autowired
        ForumEntryService forumEntryService;


        @RequestMapping(value = "/game/submitgame", method = RequestMethod.POST)
        public String submitGame(Model model, @RequestParam String titleText,
        @RequestParam(value = "coverFile") MultipartFile coverFile, // @RequestParam String cover,
        // @RequestParam List<String> genreValues, @RequestParam List<String>
        // platformValues,
        @RequestParam String playersText, @RequestParam String developerText,
        @RequestParam String releaseText, @RequestParam String publisherText,
        @RequestParam String descriptionText) throws ParseException, IOException {

                gameService.submit(titleText, coverFile, playersText, 
                        developerText, releaseText, publisherText, descriptionText);
                
                return "submitgame";
        }

        @GetMapping("/game/newgame")
        public String newGame(Model model) {
                return "submitgame";
        }

        @GetMapping("/game/{id}")
        public String getGame(Model model, @PathVariable int id) {
                customListService.showIndex(model);
                forumEntryService.showIndexLatestForumEntries(model);
                
                if(gameService.get(model, id))
                        return "game";
                else
                        return "redirect:/";
        }
}
