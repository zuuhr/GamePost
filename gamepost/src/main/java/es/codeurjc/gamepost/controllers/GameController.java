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
import es.codeurjc.gamepost.repositories.enums.GenreRepository;
import es.codeurjc.gamepost.repositories.enums.PlatformRepository;
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
        GenreRepository genreRepository;

        @Autowired
        PlatformRepository platformRepository;

        @Autowired
        GameService gameService;

        @Autowired
        CustomListService customListService;

        @Autowired
        ForumEntryService forumEntryService;


        @RequestMapping(value = "/game/submitgame", method = RequestMethod.POST)
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

                List<Genre> genres = new ArrayList<Genre>();

                if(genreAction != null) genres.add(genreRepository.findByText("Action").get());
                if(genreAdventure != null) genres.add(genreRepository.findByText("Adventure").get());
                if(genreFighting != null) genres.add(genreRepository.findByText("Fighting").get());
                if(genreRolePlaying != null) genres.add(genreRepository.findByText("Role Playing").get());
                if(genreRacing != null) genres.add(genreRepository.findByText("Racing").get());
                if(genreSimulation != null) genres.add(genreRepository.findByText("Simulation").get());
                if(genreSports != null) genres.add(genreRepository.findByText("Sports").get());
                if(genreStrategy != null) genres.add(genreRepository.findByText("Strategy").get());
                if(genreVisualNovel != null) genres.add(genreRepository.findByText("Visual Novel").get());

                List<Platform> platforms = new ArrayList<Platform>();
                if(platformWindows != null) platforms.add(platformRepository.findByText("Windows").get());
                if(platformMac != null) platforms.add(platformRepository.findByText("Mac").get());
                if(platformLinux != null) platforms.add(platformRepository.findByText("Linux").get());
                if(platformPS5 != null) platforms.add(platformRepository.findByText("Playstation 5").get());
                if(platformPS4 != null) platforms.add(platformRepository.findByText("Playstation 4").get());
                if(platformSwitch != null) platforms.add(platformRepository.findByText("Nintendo Switch").get());
                if(platformXboxSeriesXS != null) platforms.add(platformRepository.findByText("Xbox Series X/S").get());
                if(platformAndroid != null) platforms.add(platformRepository.findByText("Android").get());
                if(platformIOS != null) platforms.add(platformRepository.findByText("IOS").get());





                gameService.submit(titleText, genres, platforms, coverFile, playersText, 
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
