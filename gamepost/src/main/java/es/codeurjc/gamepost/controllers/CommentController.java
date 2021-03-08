package es.codeurjc.gamepost.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;

@Controller
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ForumEntryRepository forumEntryRepository;



    //TODO: Associate this method with the form in the web
    @RequestMapping("/reply/game/{gameid}/{forumid}/{commentid}")
    public String submitComment(Model model, @PathVariable int gameid, @PathVariable int forumid, @PathVariable int commentid, @RequestParam String title, 
        @RequestParam Comment parent, @RequestParam Content content, @RequestParam ForumEntry forumEntry)
        {
            User author = (User) model.getAttribute("user");   //TODO: Coger user de sesión
        Optional<Comment> parentComment = commentRepository.findById(commentid);
        Comment c;
        if(parentComment.isPresent()){
            c = new Comment(title, author, content, parent.getId());
        } else{ //root comment
            c = new Comment(title, author, content, forumid);
        }
        
        
        commentRepository.save(c);
        forumEntry.addComment(c);

        return "forumreply"; //TODO: Return a meaningfull html
    }

    @PostConstruct
    public void init(){
        //List<ForumEntry> forumEntry = forumEntryRepository.findAll();
        //Comment c = new Comment("title", forumEntry.get(0).getAuthor(), new Content(), forumEntry.get(0).getId());
        //commentRepository.save(c);
    }
}
