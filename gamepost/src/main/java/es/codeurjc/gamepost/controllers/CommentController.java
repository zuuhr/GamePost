package es.codeurjc.gamepost.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;

@Controller
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;

    @PostConstruct
    public void init(){
    }

    //TODO: Associate this method with the form in the web
    @RequestMapping("/submit/Comment")
    public String submitComment(Model model, @RequestParam String title, 
        @RequestParam Comment parent, @RequestParam Content content, @RequestParam ForumEntry forumEntry)
        {
        
        User author = (User) model.getAttribute("user");   //TODO: Coger user de sesión
        Comment c = commentRepository.save(new Comment(title, author, content, parent));
        
        forumEntry.addComment(c);

        return "index"; //TODO: Return a meaningfull html
    }
}
