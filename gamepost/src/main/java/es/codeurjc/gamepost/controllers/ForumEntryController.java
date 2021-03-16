package es.codeurjc.gamepost.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.Notification;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Controller
public class ForumEntryController {
    @Autowired
    private ForumEntryRepository forumEntryRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    // TODO: Associate this method with the form in the web
    @RequestMapping("/game/{gameid}/submitforumentry")
    public String submitForumEntry(Model model, @PathVariable int gameid, @RequestParam String titleText,
            @RequestParam String bodyText) {
        List<User> users = userRepository.findAll();
        Content content = new Content(bodyText, "");
        Optional<Game> game = gameRepository.findById(gameid);
        forumEntryRepository.save(new ForumEntry(titleText, users.get(0), game.get(), content));

        // TODO: Send a notification to all the users that follow this game
        for (User user : users) {
            user.addNotification(new Notification("/game/{gameid}/", "New forum entry in game {gameid}"));
        }

        String url = "redirect:/game/" + gameid;
        return url; // TODO: Return a meaningfull html
    }

    @GetMapping("/game/{gameid}/{forumid}")
    public String getForumEntry(Model model, @PathVariable int gameid, @PathVariable int forumid) {
        int forumEntryId = forumid;
        
        Optional<Game> game = gameRepository.findById(gameid);
        Optional<ForumEntry> forumEntry = forumEntryRepository.findById(forumEntryId);
        List<Comment> comments = forumEntry.get().getComments();

        List<Comment> sortedComments = commentRepository.sortComments(comments);

        if (forumEntry.isPresent()) {
            model.addAttribute("game", game.get());
            model.addAttribute("forumentry", forumEntry.get());
            model.addAttribute("comments", sortedComments);
            // TODO: model.addAttribute("user", user);

            return "forum";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/game/{gameid}/newforumentry")
    public String newForumEntry(Model model, @PathVariable int gameid) {
        Optional<Game> game = gameRepository.findById(gameid);
        model.addAttribute("game", game.get());

        return "submitforum";
    }
}