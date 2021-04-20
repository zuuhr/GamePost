package es.codeurjc.gamepost.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.services.ForumEntryService;
import es.codeurjc.gamepost.services.GameService;
import es.codeurjc.gamepost.services.ModelService;
import es.codeurjc.gamepost.services.UserService;
import es.codeurjc.gamepost.services.CustomListService;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Controller
public class ForumEntryController {
    //private Logger log = LoggerFactory.getLogger(ForumEntryController.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private ForumEntryService forumEntryService;

    @Autowired
    private CustomListService customListService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelService modelService;

    @GetMapping("/game/{gameid}/newforumentry")
    public String newForumEntry(Model model, HttpSession session, @PathVariable int gameid) {
        
        customListService.showIndex(model, session, userService.getSessionUser(session));
        forumEntryService.showIndexLatestForumEntries(model, session);

        // Show forum entries
        gameService.get(model, session, gameid);

        modelService.updateModel(model, session);

        return "submitforum";
    }

    // TODO: Associate this method with the form in the web
    @PostMapping("/game/{gameid}/submitforumentry")
    public String submitForumEntry(Model model, HttpSession session, @PathVariable int gameid, @RequestParam String titleText,
            @RequestParam String bodyText) {
        
        forumEntryService.submit(session, gameid, titleText, bodyText);

        String url = "redirect:/game/" + gameid;
        return url;
    }

    @GetMapping("/game/{gameid}/{forumid}")
    public String getForumEntry(Model model, HttpSession session, @PathVariable int gameid, @PathVariable int forumid) {
        
        customListService.showIndex(model, session, userService.getSessionUser(session));
        forumEntryService.showIndexLatestForumEntries(model, session);

        modelService.updateModel(model, session);
        return forumEntryService.view(model, session, gameid, forumid);
    }
}

