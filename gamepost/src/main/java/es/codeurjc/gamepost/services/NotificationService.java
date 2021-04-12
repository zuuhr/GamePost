package es.codeurjc.gamepost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.Notification;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CommentRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Service
public class NotificationService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FollowersService followersService;

    @Autowired
    private UserRepository userRepository;

    public void show(Model model, User user){
        
        List<Notification> notifications = user.getNotifications();
        model.addAttribute("notification", notifications);
        
        return;
    }

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

    public void sendNotificationToFollowers(int gameId, ForumEntry forumEntry){
        Game game = gameRepository.findById(gameId).get();
        List<User> users = followersService.getFollowersGame(game);

        for (User user : users) {
            user.addNotification(new Notification("/game/" + gameId, 
            "New forum entry " + 
            "in game " + game.getDescription().getName()));  

            userRepository.saveAndFlush(user);
            //log.info("Username: " + user.getName());
        }

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
