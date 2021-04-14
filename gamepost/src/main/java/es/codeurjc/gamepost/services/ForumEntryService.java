package es.codeurjc.gamepost.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.ListElement;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CustomListRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Service
public class ForumEntryService {
    
    @Autowired
    ForumEntryRepository forumEntryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    CustomListRepository customListRepository;

    @Autowired
    UserService userService;

    @Autowired
    FollowersService followersService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    CustomListService customListService;

    @Autowired
    CommentService commentService;
    
    public ForumEntry get(int id){
        Optional<ForumEntry> fe = forumEntryRepository.findById(id);
        
        if(fe.isPresent())
            return fe.get();
        else
            return null;
    }

    public ForumEntry submit(HttpSession session, int gameid, String titleText, String bodyText){
        
        User user = userService.getSessionUser(session);
        Content content = new Content(bodyText, "");
        Game game = gameRepository.findById(gameid).get();
        
        ForumEntry fe = forumEntryRepository.save(new ForumEntry(titleText, user, game, content));

        //Send a notification to the users that follow this game
        notificationService.sendNotificationToFollowers(gameid, fe);

        return fe;
    }

    public void showIndexLatestForumEntries(Model model){
        model.addAttribute("latestposts", forumEntryRepository.findTop20ByOrderByLastUpdatedOnDesc());
    }

    public String view(Model model, int gameId, int forumId){
    
        Optional<ForumEntry> forumEntry = forumEntryRepository.findById(forumId);
        Optional<User> user = userRepository.findByName("Mariam");

        if (forumEntry.isPresent()) {
            //Pick comments
            Optional<Game> game = gameRepository.findById(gameId);
            List<Comment> comments = forumEntry.get().getComments();

            //Sort them
            List<Comment> sortedComments = commentService.sort(comments);
            //List<Comment> sortedComments = comments;

            //???
            List<CustomComment> customComments = new ArrayList<CustomComment>();
            for (Comment comment : sortedComments) {
                customComments.add(new CustomComment(comment));
            }

            //Show info of the page
            // TODO: FIX THIS and put it into its correct place
            if (user.isPresent()) {
                List<CustomList<ListElement>> customLists = customListRepository.findByUser(user.get());
                model.addAttribute("list", customLists);
                //model.addAttribute("user", user.get());
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

    // TODO: Revisar esta organización
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

    // TODO: Revisar la organización de esto.
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
