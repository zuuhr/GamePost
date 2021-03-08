package es.codeurjc.gamepost.controllers;

import java.util.Arrays;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.Forum;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.ForumRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Controller
public class ForumEntryController {
    @Autowired
    private ForumEntryRepository forumEntryRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
    }

    //TODO: Associate this method with the form in the web
    @RequestMapping("/submit/ForumEntry")
    public String submitForumEntry(Model model, @RequestParam String title, @RequestParam Content content, @RequestParam Forum forum)
    {
        
        User author = (User) model.getAttribute("user");   //TODO: Coger user de sesión
        ForumEntry fe = forumEntryRepository.save(new ForumEntry(title, author, content));
        
        forum.addForumEntry(fe);

        return "index"; //TODO: Return a meaningfull html
    }

    @GetMapping("/game/{gameid}/{forumid}")
    public String getForumEntry(Model model, @PathVariable int gameid, @PathVariable int forumid){
        Optional<Game> game = gameRepository.findById(gameid);
        Optional<ForumEntry> forum = forumEntryRepository.findById(forumid);
        
                
        if(forum.isPresent()){
            model.addAttribute("game", game.get());
            model.addAttribute("forumentry", forum.get());
            model.addAttribute("comments", forum.get().getComments());
            
 
            return "forum";
        } else {
            return "redirect:/";
        }
    }
}
