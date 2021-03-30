package es.codeurjc.gamepost.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

@Service
public class CustomListService {

    //#region Dependencies

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomListRepository customListRepository;

    @Autowired
    ForumEntryRepository forumEntryRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    CommentRepository commentRepository;

    //#endregion

    public CustomList<ListElement> submit(String nameText){
        User author = userRepository.findAll().get(0);   //TODO: Coger user de sesión
        
        CustomList<ListElement> cl = new CustomList<ListElement>(nameText, author);
        author.addMyList(cl);
        customListRepository.save(cl);

        return cl;
    }

    public void view(Model model, int userId, int listId){

        // TODO: get user from session
        Optional<User> user = userRepository.findByName("Mariam");
        if (user.isPresent()) {
            List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get());
            model.addAttribute("list", customLists);
            model.addAttribute("user", user.get());
        }
        // Show forum entries
        model.addAttribute("latestposts", forumEntryRepository.findTop20ByOrderByLastUpdatedOnDesc());
        
        Optional<CustomList<ListElement>> cl = customListRepository.findById(listId);
        
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
        //Optional<User> user = userRepository.findByName("Mariam");
        //if(user.isPresent()){
        //    List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get()); 
        //    model.addAttribute("list", customLists);
        //    model.addAttribute("user", user.get());
        //}
        
        return;
    }

    public void addGame(int gameId, int listId){
        CustomList<ListElement> customList = customListRepository.findById(listId).get();
        customList.addElement(gameRepository.findById(gameId).get());
        customListRepository.save(customList);
    }

    public void addForumEntry(int forumEntryId, int listId){
        CustomList<ListElement> customList = customListRepository.findById(listId).get();
        ForumEntry forumEntry = forumEntryRepository.findById(forumEntryId).get();
        customList.addElement(forumEntry);
        customListRepository.save(customList);
    }

    public void addComment(int commentId, int listId){
        CustomList<ListElement> customList = customListRepository.findById(listId).get();
        Comment comment = commentRepository.findById(commentId).get();
        customList.addElement(comment);
        customListRepository.save(customList);

        return;
    }

}