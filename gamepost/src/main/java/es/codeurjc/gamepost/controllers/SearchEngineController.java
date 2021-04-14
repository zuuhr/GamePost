package es.codeurjc.gamepost.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.services.CustomListService;
import es.codeurjc.gamepost.services.ForumEntryService;
import es.codeurjc.gamepost.services.GameService;
import es.codeurjc.gamepost.services.UserService;

@Controller
public class SearchEngineController {
    
    @Autowired
    GameService gameService;

    @Autowired
    CustomListService customListService;

    @Autowired
    ForumEntryService forumEntryService;

    @Autowired
    UserService userService;

    @PostMapping("/search")
    public String search(Model model, HttpSession session, @RequestParam String searchText){
        
        gameService.search(model, searchText);
        customListService.showIndex(model, userService.getSessionUser(session));
        forumEntryService.showIndexLatestForumEntries(model);
        
        return "searchresults";
    }
}
