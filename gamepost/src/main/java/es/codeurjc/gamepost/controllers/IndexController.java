package es.codeurjc.gamepost.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.services.ForumEntryService;
import es.codeurjc.gamepost.services.GameService;
import es.codeurjc.gamepost.services.ModelService;
import es.codeurjc.gamepost.services.UserService;

@Controller
public class IndexController {

    @Autowired
    ForumEntryService forumEntryService;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @Autowired
    ModelService modelService;

    private Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String enlace(Model model, HttpServletRequest request, HttpSession session) throws NullPointerException {
        try {
            if (session.isNew()) {
                session.setAttribute("logged", false);
                userService.setRoleAnonymous(model, request);
            }

            // log.info("INFO: Try");
            // log.info("INFO: User name: " + (session.getAttribute("username")));

            if ((boolean) session.getAttribute("logged")) {
                // log.info("INFO: Try");

                User user = userService.getSessionUser(session);

                log.info("INFO: User id is " + user.getId() + " in index controller.");
                gameService.showIndexGamesUserPreferences(model, session, user);
                // gameService.showIndexLatestUpdatedGames(model, session);
                userService.loadInfo(model, session);
            } else
                gameService.showIndexLatestUpdatedGames(model, session);

            forumEntryService.showIndexLatestForumEntries(model, session);

            modelService.updateModel(model, session);
            return "index";
        } catch (Exception e) {
            session.setAttribute("logged", false);
            userService.setRoleAnonymous(model, request);
            gameService.showIndexLatestUpdatedGames(model, session);
            forumEntryService.showIndexLatestForumEntries(model, session);
            modelService.updateModel(model, session);
            return "index";
        }
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

        forumEntryService.showIndexLatestForumEntries(model, session);
        userService.loadInfo(model, session);

        modelService.updateModel(model, session);
        return "profile";
    }

    @GetMapping("notifications")
    public String notifications(Model model, HttpSession session) {

        forumEntryService.showIndexLatestForumEntries(model, session);
        userService.loadInfo(model, session);

        modelService.updateModel(model, session);
        return "notifications";
    }
}
