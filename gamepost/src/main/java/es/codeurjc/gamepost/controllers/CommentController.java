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

    @RequestMapping("/game/{gameid}/{forumid}/{commentid}/reply")
    public String submitComment(Model model, @PathVariable int gameid, @PathVariable int forumid,
            @PathVariable int commentid, @RequestParam String contentText) {
        User author = (User) model.getAttribute("user"); // TODO: Coger user de sesión
        //Generate content TODO: add images
        Content content = new Content(contentText, "");        
        Optional<Comment> parentComment = commentRepository.findById(commentid);
        Comment c;
        if (parentComment.isPresent()) {
            c = new Comment(author, content, parentComment.get().getId());
        } else { // root comment
            c = new Comment(author, content, forumid);
        }

        commentRepository.save(c);
        ForumEntry forumEntry = forumEntryRepository.getOne(forumid);
        forumEntry.addComment(c);
        // TODO: Update post within database
        //forumEntryRepository.save(forumEntry);
        //forumEntryRepository.save(forumEntryRepository.getOne(forumid));
        
        String url = "redirect:/game/" + gameid + "/" + forumid;

        return url; // TODO: Return a meaningfull html
    }

    @PostConstruct
    public void init() {
        // List<ForumEntry> forumEntry = forumEntryRepository.findAll();
        // Comment c = new Comment("title", forumEntry.get(0).getAuthor(), new
        // Content(), forumEntry.get(0).getId());
        // commentRepository.save(c);
    }
}
