package es.codeurjc.gamepost.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Notification;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Controller
public class CommentController {
    private Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ForumEntryRepository forumEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/game/{gameid}/{forumid}/{commentid}/reply")
    public String submitComment(Model model, @PathVariable int gameid, @PathVariable int forumid,
            @PathVariable int commentid, @RequestParam String contentText) {
        
        // TODO: Coger user de sesión
        //User author = (User) model.getAttribute("user"); 
        
        User author = userRepository.findByName("Julen").get();
        ForumEntry forumEntry = forumEntryRepository.findById(forumid).get();
        
        
        //Generate content TODO: add images
        Content content = new Content(contentText, "");  

        Optional<Comment> parentComment = commentRepository.findById(commentid);
        Comment comment;
        if (parentComment.isPresent()) {
            comment = new Comment(author, parentComment.get().getId(), content);
        } else { // root comment
            comment = new Comment(author, forumid, content);
        }

        //Send notification to author
        if(parentComment.isPresent())
            parentComment.get().getAuthor().addNotification(new Notification("/game/{gameid}/{forumid}/{commentid}", "Someone replyied to your comment"));
        
        //TODO: Send notification to all users that follow this forumEntry
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.addNotification(new Notification("/game/{gameid}/", "New forum entry in game {gameid}"));    
        }
        
        forumEntry.addComment(comment);
        forumEntryRepository.saveAndFlush(forumEntry);

        log.info("Comment submitted. Author:" + author.getId());
        
        // TODO: Update post within database
        //forumEntryRepository.save(forumEntryRepository.getOne(forumid));
        
        String url = "redirect:/game/" + gameid + "/" + forumid;

        return url; // TODO: Return a meaningfull html
    }
}
