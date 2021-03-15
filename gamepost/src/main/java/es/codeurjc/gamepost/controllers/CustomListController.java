package es.codeurjc.gamepost.controllers;


import java.util.LinkedList;
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
import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.ListElement;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CustomListRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Controller
public class CustomListController {
    
    @Autowired
    CustomListRepository customListRepository;

    @Autowired
    UserRepository userRepository;

    //TODO: Associate this method with the form in the web
    @RequestMapping("/list/newlist")
    public String submitCustomList(Model model, @RequestParam String nameText){
        
        User author = userRepository.findAll().get(0);   //TODO: Coger user de sesión
        
        CustomList<ListElement> cl = new CustomList<>(nameText, author);
        author.addMyList(cl);
        customListRepository.save(cl);
        return "redirect:/"; //TODO: Return a meaningfull html
    }

    @GetMapping("list/{userid}/{listid}")
    public String viewList(Model model, @PathVariable int userid, @PathVariable int listid){

        Optional<CustomList<ListElement>> cl = customListRepository.findById(listid);
        
        model.addAttribute("customlist", cl.get());

        if(cl.get().getElement(0) instanceof Game){
            List<Game> games  = new LinkedList<Game>();
            for (ListElement lElement : cl.get().getAllElements()){
                games.add((Game) lElement);
            }
            model.addAttribute("games", games);
        } else if (cl.get().getElement(0) instanceof ForumEntry){
            List<ForumEntry> forumEntries  = new LinkedList<ForumEntry>();
            for (ListElement lElement : cl.get().getAllElements()){
                forumEntries.add((ForumEntry) lElement);
            }
          model.addAttribute("posts", forumEntries);
            
        } else if(cl.get().getElement(0) instanceof Comment){
            List<Comment> comments  = new LinkedList<Comment>();
            for (ListElement lElement : cl.get().getAllElements()){
                comments.add((Comment) lElement);
            }
            model.addAttribute("comments", comments);

        }
        Optional<User> user = userRepository.findByName("Mariam");
        if(user.isPresent()){
            List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get()); 
            model.addAttribute("list", customLists);
            model.addAttribute("user", user.get());
        }
        
        
        return "list";
    }

}
