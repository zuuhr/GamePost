package es.codeurjc.gamepost.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.ListElement;
import es.codeurjc.gamepost.objects.Notification;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.CustomListRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;
import es.codeurjc.gamepost.services.FollowersService;
import es.codeurjc.gamepost.services.ForumEntryService;
import es.codeurjc.gamepost.services.CommentService;
import es.codeurjc.gamepost.services.CustomListService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ForumEntryController {
    private Logger log = LoggerFactory.getLogger(ForumEntryController.class);

    @Autowired
    private ForumEntryRepository forumEntryRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CustomListRepository customListRepository;

    @Autowired
    private CommentService sortCommentsService;

    @Autowired
    private FollowersService followersService;

    @Autowired
    private ForumEntryService forumEntryService;

    @Autowired
    private CustomListService customListService;

    @GetMapping("/game/{gameid}/newforumentry")
    public String newForumEntry(Model model, @PathVariable int gameid) {
        
        customListService.showIndex(model);
        forumEntryService.showIndexLatestForumEntries(model);

        // Show forum entries
        Optional<Game> game = gameRepository.findById(gameid);
        model.addAttribute("game", game.get());

        return "submitforum";
    }

    // TODO: Associate this method with the form in the web
    @RequestMapping("/game/{gameid}/submitforumentry")
    public String submitForumEntry(Model model, @PathVariable int gameid, @RequestParam String titleText,
            @RequestParam String bodyText) {
        
        forumEntryService.submit(gameid, titleText, bodyText);

        String url = "redirect:/game/" + gameid;
        return url; // TODO: Return a meaningfull html
    }

    @GetMapping("/game/{gameid}/{forumid}")
    public String getForumEntry(Model model, @PathVariable int gameid, @PathVariable int forumid) {
        
        customListService.showIndex(model);
        forumEntryService.showIndexLatestForumEntries(model);

        return forumEntryService.view(model, gameid, forumid);
    }
}

