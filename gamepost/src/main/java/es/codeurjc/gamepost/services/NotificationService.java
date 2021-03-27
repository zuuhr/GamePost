package es.codeurjc.gamepost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.Notification;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.GameRepository;

@Service
public class NotificationService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FollowersService followersService;

    public void sendNotificationToAuthor(int gameId, int parentCommentId, Comment comment){
        Game game = gameRepository.findById(gameId).get();
        Optional<Comment> parentComment = commentRepository.findById(parentCommentId);

        if(parentComment.isPresent())
            parentComment.get().getAuthor().addNotification(
                new Notification(
                    "/game/"+ gameId +"/"+ comment.getForumEntry().getId(), 
                    "New forum entry in game" + game.getDescription().getName()));

        return;
    }

    public void sendNotificationToFollowers(int gameId, Comment comment){
        List<List<User>> users = followersService.getFollowersSeparated(comment);
        Game game = gameRepository.findById(gameId).get();
        
        //Parent comment followers
        for (User user : users.get(0)) {
            user.addNotification(new Notification("/game/" + gameId + "/" + comment.getForumEntry().getId(), 
            "New comment " +
            "in thread " + comment.getParent().getContent().getText().substring(0, 10) + "..." +
            " in forum entry " + comment.getForumEntry().getTitle() + 
            " in game " + game.getDescription().getName()));  
        }

        //ForumEntry followers
        for (User user : users.get(1)) {
            user.addNotification(new Notification("/game/" + gameId + "/" + comment.getForumEntry().getId(), 
            "New comment " + 
            "in forum entry " + comment.getForumEntry().getTitle() + 
            " in game " + game.getDescription().getName()));    
        }

        //Game followers
        for (User user : users.get(2)) {
            user.addNotification(new Notification("/game/" + gameId, 
            "New comment " + 
            "in game " + game.getDescription().getName()));  
        }

        return;
    }
}
