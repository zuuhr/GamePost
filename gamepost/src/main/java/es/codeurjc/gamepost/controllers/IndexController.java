package es.codeurjc.gamepost.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.gamepost.services.ForumEntryService;
import es.codeurjc.gamepost.services.GameService;
import es.codeurjc.gamepost.services.UserService;

@Controller
public class IndexController {

    @Autowired
    ForumEntryService forumEntryService;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public String enlace(Model model, HttpServletRequest request, HttpSession session) {
        
        if(session.isNew()){
            session.setAttribute("logged", false);
            userService.setRoleAnonymous(model, request);
        }

        gameService.showIndexLatestUpdatedGames(model);
        forumEntryService.showIndexLatestForumEntries(model);
        userService.loadInfo(model, session);

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
    public String profile(Model model, HttpSession session) {
        
        forumEntryService.showIndexLatestForumEntries(model);
        userService.loadInfo(model, session);

        return "profile";
    }

    @GetMapping("notifications")
    public String notifications(Model model, HttpSession session){
        
        forumEntryService.showIndexLatestForumEntries(model);
        userService.loadInfo(model, session);
        
        return "notifications";
    }    
}
