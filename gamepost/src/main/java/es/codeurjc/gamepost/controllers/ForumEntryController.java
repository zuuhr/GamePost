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
    FollowersService followersService;

    @Autowired
    ForumEntryService forumEntryService;

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
        // TODO: get user from session
        Optional<User> user = userRepository.findByName("Mariam");
        if (user.isPresent()) {
            List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get());
            model.addAttribute("list", customLists);
            model.addAttribute("user", user.get());
        }
        // Show forum entries
        model.addAttribute("latestposts", forumEntryRepository.findTop20ByOrderByLastUpdatedOnDesc());

        Optional<ForumEntry> forumEntry = forumEntryRepository.findById(forumid);

        if (forumEntry.isPresent()) {
            Optional<Game> game = gameRepository.findById(gameid);
            List<Comment> comments = forumEntry.get().getComments();

            List<Comment> sortedComments = sortCommentsService.sort(comments);
            //List<Comment> sortedComments = comments;

            List<CustomComment> customComments = new ArrayList<CustomComment>();
            for (Comment comment : sortedComments) {
                customComments.add(new CustomComment(comment));
            }
            if (user.isPresent()) {
                List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get());
                model.addAttribute("list", customLists);
                model.addAttribute("user", user.get());
                // get forumentry lists
                List<CustomList<ListElement>> forumEntryLists = getUserCustomListsForumEntry(user.get());
                model.addAttribute("customforumentrylist", forumEntryLists);

                // get comment lists
                List<CustomList<ListElement>> commentLists = getUserCustomListsComment(user.get());
                model.addAttribute("customcommentlist", commentLists);
            }
            model.addAttribute("game", game.get());
            model.addAttribute("forumentry", forumEntry.get());
            model.addAttribute("comments", customComments);

            return "forum";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/game/{gameid}/newforumentry")
    public String newForumEntry(Model model, @PathVariable int gameid) {
        // TODO: get user from session
        Optional<User> user = userRepository.findByName("Mariam");
        if (user.isPresent()) {
            List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get());
            model.addAttribute("list", customLists);
            model.addAttribute("user", user.get());
        }
        // Show forum entries
        model.addAttribute("latestposts", forumEntryRepository.findTop20ByOrderByLastUpdatedOnDesc());
        Optional<Game> game = gameRepository.findById(gameid);
        model.addAttribute("game", game.get());

        return "submitforum";
    }



    public List<CustomList<ListElement>> getUserCustomListsForumEntry(User user){
        
        List<CustomList<ListElement>> customLists = customListRepository.findByUser(user);
        List<CustomList<ListElement>> forumEntryLists = new LinkedList<CustomList<ListElement>>();
        for (CustomList<ListElement> customList : customLists) {
            if(customList.getAllElements().isEmpty() || customList.getElement(0) instanceof ForumEntry){
                if(!customList.getName().equals("[Comments]") && !customList.getName().equals("[Games]"))
                forumEntryLists.add( customList);
            }

        }
        return forumEntryLists;
    }

    public List<CustomList<ListElement>> getUserCustomListsComment(User user){
        
        List<CustomList<ListElement>> customLists = customListRepository.findByUser(user);
        List<CustomList<ListElement>> commentLists = new LinkedList<CustomList<ListElement>>();
        for (CustomList<ListElement> customList : customLists) {
            if(customList.getAllElements().isEmpty() || customList.getElement(0) instanceof Comment){
                if(!customList.getName().equals("[ForumEntries]") && !customList.getName().equals("[Games]"))
                commentLists.add( customList);
            }
        }
        return commentLists;
    }
}

class CustomComment {

    int commentid;
    User author;
    ForumEntry forumEntry;
    Content content;
    List<Comment> parent = new ArrayList<Comment>();
    List<Comment> childs;
    Date postedOn;
    int childness;

    public CustomComment(Comment comment) {
        this.commentid = comment.getId();
        this.author = comment.getAuthor();
        this.forumEntry = comment.getForumEntry();
        this.content = comment.getContent();
        this.parent.add(comment.getParent());
        this.postedOn = comment.getPostedOn();
        this.childness = comment.getChildness(); 
    }


    
}