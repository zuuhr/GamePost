package es.codeurjc.gamepost.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Service
public class CommentService {

    //#region Dependencies

    @Autowired
    UserRepository userRepository;

    @Autowired
    ForumEntryRepository forumEntryRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    NotificationService notificationService;

    //#endregion

    public Comment submit(/*HttpSession httpSession,*/ int gameId, int forumId, int commentId, String contentText){
        // TODO: Coger user de sesión
        //User author = (User) model.getAttribute("user"); 
        
        User author = userRepository.findByName("Julen").get();
        ForumEntry forumEntry = forumEntryRepository.findById(forumId).get();
        
        //Generate content 
        //TODO: add images
        Content content = new Content(contentText, "");  

        Optional<Comment> parentComment = commentRepository.findById(commentId);
        Comment comment;
        if (parentComment.isPresent()) {
            comment = new Comment(author, forumEntry, parentComment.get(), content);
        } else { // root comment
            comment = new Comment(author, forumEntry, null, content);
        }

        //Send notification to author
        notificationService.sendNotificationToAuthor(gameId, commentId, comment);

        //Send notification to all users following the item
        notificationService.sendNotificationToFollowers(gameId, comment);

        //Add the comment to the database
        forumEntry.addComment(comment);
        forumEntryRepository.saveAndFlush(forumEntry);

        return comment;
    }
    
    public List<Comment> sort(List<Comment> comments){
        List<Comment> result = new ArrayList<Comment>();

        for (Comment comment : comments) {
            if(!result.contains(comment)){
                Comment parent = comment.getParent();
                
                //Comment raíz
                if(parent == null){
                    result.add(comment);
                    continue;
                }

                //If the parent already is in the result List, insert the child after him.
                if(result.contains(parent)){
                    if(result.size() == result.indexOf(parent) + 1)
                        result.add(comment);
                    else
                        result.add(result.indexOf(parent) + 1, comment);
                }

                //Else, make the correspondant subcalls.
                else
                {
                    List<Comment> subResult = sort(new ArrayList<Comment>(Arrays.asList(comment.getParent())));
                    for (Comment comment2 : subResult) {
                        result.add(comment2);
                    }
                    result.add(comment);
                }
            }
        }

        return result;
    }
}
