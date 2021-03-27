package es.codeurjc.gamepost.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@Service
public class SubmitElementService {

    //#region Services

    @Autowired
    private NotificationService notificationService;

    //#endregion

    //#region Repositories

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForumEntryRepository forumEntryRepository;

    @Autowired
    private CommentRepository commentRepository;

    //#endregion

    public Comment submitComment(/*HttpSession httpSession,*/ int gameId, int forumId, int commentId, String contentText){
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
}
