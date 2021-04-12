package es.codeurjc.gamepost.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.services.CustomListService;
import es.codeurjc.gamepost.services.ForumEntryService;
import es.codeurjc.gamepost.services.UserService;

@Controller
public class CustomListController {
    
    @Autowired
    CustomListService customListService;

    @Autowired
    ForumEntryService forumEntryService;

    @Autowired
    UserService userService;

    //TODO: Associate this method with the form in the web
    @RequestMapping("/list/newlist")
    public String submitCustomList(Model model, HttpSession session, @RequestParam String nameText){
        
        customListService.submit(session, nameText);

        return "redirect:/";
    }

    @GetMapping("list/{userid}/{listid}")
    public String viewList(Model model, HttpSession session, @PathVariable int userid, @PathVariable int listid){
        
        //TODO: Avoid someone being able to snoop into other user's customLists.
        User user = userService.getSessionUser(session);
        customListService.view(model, user, listid);
        
        return "list";
    }

    @GetMapping("/list/add/game/{listid}/{gameid}")
    public String addGameToList(Model model, @PathVariable int listid, @PathVariable int gameid){
        
        customListService.addGame(gameid, listid);

        return "redirect:/game/" + gameid;
    }
    
    @GetMapping("/list/add/forumentry/{listid}/{forumentryid}")
    public String addForumEntryToList(Model model, @PathVariable int listid, @PathVariable int forumentryid){
        
        customListService.addForumEntry(forumentryid, listid);

        return "redirect:/game/" + forumEntryService.get(forumentryid).getGame().getId();
    }

    @GetMapping("/list/add/comment/{listid}/{forumentryid}/{commentid}")
    public String addCommentToList(Model model, @PathVariable int listid, @PathVariable int forumentryid, @PathVariable int commentid){
        
        customListService.addComment(commentid, listid);

        return "redirect:/game/" + forumEntryService.get(forumentryid).getGame().getId() + "/" + forumentryid;
    }
    
    @GetMapping("/list/games/add/game/{gameid}")
    public String followGame(Model model, HttpSession session, @PathVariable int gameid){

        User user = userService.getSessionUser(session);
        customListService.addGame(gameid, user.getGames().getId());
        
        return "redirect:/game/" + gameid;
    }

    @GetMapping("/list/forumentries/add/forumentry/{forumentryid}")
    public String followForumEntry(Model model, HttpSession session, @PathVariable int forumentryid){
        
        User user = userService.getSessionUser(session);
        customListService.addForumEntry(forumentryid, user.getForumEntries().getId());

        return "redirect:/game/" + forumEntryService.get(forumentryid).getGame().getId() + "/" + forumentryid;
    }

    @GetMapping("/list/comments/add/comment/{forumentryid}/{commentid}")
    public String followComment(Model model, HttpSession session, @PathVariable int forumentryid, @PathVariable int commentid){
        
        User user = userService.getSessionUser(session);
        customListService.addComment(commentid, user.getComments().getId());

        return "redirect:/game/" + forumEntryService.get(forumentryid).getGame().getId() + "/" + forumentryid;
    }
}
