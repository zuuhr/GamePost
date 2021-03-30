package es.codeurjc.gamepost.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.services.CommentService;

@Controller
public class CommentController {
    private Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @RequestMapping("/game/{gameid}/{forumid}/{commentid}/reply")
    public String submitComment(Model model, @PathVariable int gameid, 
            @PathVariable int forumid, @PathVariable int commentid, @RequestParam String contentText) {
        
        // Submit
        commentService.submit(/*httpSession,*/ gameid, forumid, commentid, contentText);

        // Return
        log.info("Comment submitted.");

        String url = "redirect:/game/" + gameid + "/" + forumid;
        return url;
    }
}
