package es.codeurjc.gamepost.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.repositories.enums.GenreRepository;
import es.codeurjc.gamepost.repositories.enums.PlatformRepository;
import es.codeurjc.gamepost.services.CustomListService;
import es.codeurjc.gamepost.services.ForumEntryService;
import es.codeurjc.gamepost.services.GameEnumService;
import es.codeurjc.gamepost.services.GameService;
import es.codeurjc.gamepost.services.UserService;


@Controller
public class GameController {

        @Autowired
        GenreRepository genreRepository;

        @Autowired
        PlatformRepository platformRepository;

        @Autowired
        GameService gameService;

        @Autowired
        GameEnumService gameEnumService;

        @Autowired
        CustomListService customListService;

        @Autowired
        ForumEntryService forumEntryService;

        @Autowired 
        UserService userService;


        @PostMapping(value = "/game/submitgame")
        public String submitGame(Model model, @RequestParam String titleText,
        @RequestParam(value = "coverFile") MultipartFile coverFile, // @RequestParam String cover,
        @RequestParam(required = false, name = "genreAction") String genreAction,
        @RequestParam(required = false, name = "genreAdventure") String genreAdventure,
        @RequestParam(required = false, name = "genreFighting") String genreFighting,
        @RequestParam(required = false, name = "genreRolePlaying") String genreRolePlaying,
        @RequestParam(required = false, name = "genreRacing") String genreRacing,
        @RequestParam(required = false, name = "genreSimulation") String genreSimulation,
        @RequestParam(required = false, name = "genreSports") String genreSports,
        @RequestParam(required = false, name = "genreStrategy") String genreStrategy,
        @RequestParam(required = false, name = "genreVisualNovel") String genreVisualNovel,

        @RequestParam(required = false, name = "platformWindows") String platformWindows,
        @RequestParam(required = false, name = "platformMac") String platformMac,
        @RequestParam(required = false, name = "platformLinux") String platformLinux,
        @RequestParam(required = false, name = "platformPS5") String platformPS5,
        @RequestParam(required = false, name = "platformPS4") String platformPS4,
        @RequestParam(required = false, name = "platformSwitch") String platformSwitch,
        @RequestParam(required = false, name = "platformXboxSeriesXS") String platformXboxSeriesXS,
        @RequestParam(required = false, name = "platformAndroid") String platformAndroid,
        @RequestParam(required = false, name = "platformIOS") String platformIOS,
        // @RequestParam List<String> genreValues, @RequestParam List<String>
        // platformValues,
        @RequestParam String playersText, @RequestParam String developerText,
        @RequestParam String releaseText, @RequestParam String publisherText,
        @RequestParam String descriptionText) throws ParseException, IOException {

                List<Genre> genres = gameEnumService.getGenreList(genreAction, genreAdventure, genreFighting, genreRolePlaying, genreRacing, genreSimulation, genreSports, genreStrategy, genreVisualNovel);
                List<Platform> platforms = gameEnumService.getPlatformList(platformWindows, platformMac, platformLinux, platformPS5, platformPS4, platformSwitch, platformXboxSeriesXS, platformAndroid, platformIOS);                

                gameService.submit(titleText, genres, platforms, coverFile, playersText, 
                        developerText, releaseText, publisherText, descriptionText);
                
                return "submitgame";
        }
        
        @GetMapping("/game/newgame")
        public String newGame(Model model) {
                return "submitgame";
        }

        @GetMapping("/game/{id}")
        public String getGame(Model model, HttpSession session, @PathVariable int id) {
                customListService.showIndex(model, userService.getSessionUser(session));
                forumEntryService.showIndexLatestForumEntries(model);
                
                if(gameService.get(model, id))
                        return "game";
                else
                        return "redirect:/";
        }
}
