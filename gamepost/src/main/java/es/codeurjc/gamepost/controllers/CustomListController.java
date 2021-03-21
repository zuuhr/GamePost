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
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.CustomListRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Controller
public class CustomListController {
    
    @Autowired
    CustomListRepository customListRepository;

    
    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ForumEntryRepository forumEntryRepository;

    @Autowired
    CommentRepository commentRepository;

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

        if(cl.get().getAllElements().isEmpty()){

        }
        else if(cl.get().getElement(0) instanceof Game){
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

    @GetMapping("/list/add/game/{listid}/{gameid}")
    public String addGameToList(Model model, @PathVariable int listid, @PathVariable int gameid){
        CustomList<ListElement> customList = customListRepository.findById(listid).get();
        customList.addElement(gameRepository.findById(gameid).get());
        customListRepository.save(customList);
        return "redirect:/game/" + gameid;
    }
    @GetMapping("/list/add/forumentry/{listid}/{forumentryid}")
    public String addForumEntryToList(Model model, @PathVariable int listid, @PathVariable int forumentryid){
        CustomList<ListElement> customList = customListRepository.findById(listid).get();
        ForumEntry forumEntry = forumEntryRepository.findById(forumentryid).get();
        customList.addElement(forumEntry);
        customListRepository.save(customList);
        return "redirect:/game/" + forumEntry.getGame().getId();
    }
    @GetMapping("/list/add/comment/{listid}/{forumentryid}/{commentid}")
    public String addToList(Model model, @PathVariable int listid, @PathVariable int forumentryid, @PathVariable int commentid){
        CustomList<ListElement> customList = customListRepository.findById(listid).get();
        ForumEntry forumEntry = forumEntryRepository.findById(forumentryid).get();
        Comment comment = commentRepository.findById(commentid).get();
        customList.addElement(comment);
        customListRepository.save(customList);
        return "redirect:/game/" + forumEntry.getGame().getId() + "/" + forumentryid;
    }
    
    @GetMapping("/list/games/add/game/{gameid}")
    public String followGame(Model model, @PathVariable int gameid){
        User user = userRepository.findByName("Mariam").get(); //TODO: get user from session
        Game game = gameRepository.findById(gameid).get();
        CustomList<ListElement> customList = customListRepository.findById(user.getGames().getId()).get();
        if(!user.getGames().getAllElements().contains(game)){
            customList.addElement(game);
            customListRepository.save(customList);
        }
        return "redirect:/game/" + gameid;
    }

    @GetMapping("/list/forumentries/add/forumentry/{forumentryid}")
    public String followForumEntry(Model model, @PathVariable int forumentryid){
        User user = userRepository.findByName("Mariam").get(); //TODO: get user from session
        ForumEntry forumEntry = forumEntryRepository.findById(forumentryid).get();
        CustomList<ListElement> customList = customListRepository.findById(user.getForumEntries().getId()).get();
        if(!user.getForumEntries().getAllElements().contains(forumEntry)){
            customList.addElement(forumEntry);
            customListRepository.save(customList);
        }
        return "redirect:/game/" + forumEntry.getGame().getId() + "/" + forumentryid;
    }

    @GetMapping("/list/comments/add/comment/{forumentryid}/{commentid}")
    public String followComment(Model model, @PathVariable int forumentryid, @PathVariable int commentid){
        User user = userRepository.findByName("Mariam").get(); //TODO: get user from session
        Comment comment = commentRepository.findById(commentid).get();
        CustomList<ListElement> customList = customListRepository.findById(user.getComments().getId()).get();
        if(!user.getComments().getAllElements().contains(comment)){
            customList.addElement(comment);
            customListRepository.save(customList);
        }
        return "redirect:/game/" + comment.getForumEntry().getGame().getId() + "/" + forumentryid;
    }

    public List<CustomList<ListElement>> getUserCustomListsGame(User user){
        
        List<CustomList<ListElement>> customLists = customListRepository.findByUser(user);
        List<CustomList<ListElement>> gameLists = new LinkedList<CustomList<ListElement>>();
        for (CustomList<ListElement> customList : customLists) {
            if(customList.getAllElements().isEmpty() || customList.getElement(0) instanceof Game){
                gameLists.add( customList);
                
            }
        }
        return gameLists;
    }

    public List<CustomList<ListElement>> getUserCustomListsForumEntry(User user){
        
        List<CustomList<ListElement>> customLists = customListRepository.findByUser(user);
        List<CustomList<ListElement>> forumEntryLists = new LinkedList<CustomList<ListElement>>();
        for (CustomList<ListElement> customList : customLists) {
            if(customList.getAllElements().isEmpty() || customList.getElement(0) instanceof ForumEntry){
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
                commentLists.add( customList);
                
            }
        }
        return commentLists;
    }


}
