package es.codeurjc.gamepost.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.Comment;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

@Service
public class FollowersService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getFollowers(Comment comment){
        List<User> matches = new ArrayList<User>();
        
        //Followers of parent comment
        Comment c = comment.getParent();

        //Followers of the forum Entry
        ForumEntry fe = comment.getForumEntry();

        //Followers of the game
        Game g = comment.getForumEntry().getGame();

        //Find among users
        for (User u : userRepository.findAll()) {
            if(
                u.getComments().contains(c) ||
                u.getForumEntries().contains(fe) ||
                u.getGames().contains(g)
            ){
                matches.add(u);
            }
        }

        return matches;
    }

    public List<User> getFollowersComment(Comment comment){
        List<User> matches = new ArrayList<User>();
        
        //Followers of parent comment
        Comment c = comment.getParent();

        //Find among users
        for (User u : userRepository.findAll()) {
            if(
                u.getComments().contains(c) 
                //u.getForumEntries().contains(fe) ||
                //u.getGames().contains(g)
            ){
                matches.add(u);
            }
        }

        return matches;
    }

    public List<User> getFollowersForumEntry(Comment comment){
        List<User> matches = new ArrayList<User>();

        //Followers of the forum Entry
        ForumEntry fe = comment.getForumEntry();

        //Find among users
        for (User u : userRepository.findAll()) {
            if(
                u.getForumEntries().contains(fe) 
            ){
                matches.add(u);
            }
        }

        return matches;
    }

    public List<User> getFollowersGame(Comment comment){

        List<User> matches = new ArrayList<User>();

        //Followers of the game
        Game g = comment.getForumEntry().getGame();

        //Find among users
        for (User u : userRepository.findAll()) {
            if(
                u.getGames().contains(g)
            ){
                matches.add(u);
            }
        }

        return matches;
    }

    public List<User> getFollowersGame(Game g){

        List<User> matches = new ArrayList<User>();

        //Find among users
        for (User u : userRepository.findAll()) {
            if(
                u.getGames().contains(g)
            ){
                matches.add(u);
            }
        }

        return matches;
    }

    public List<List<User>> getFollowersSeparated(Comment comment){
        //Init data structures
        List<List<User>> matches = new ArrayList<List<User>>();
        for(int i = 0; i<3; i++)
            matches.add(new ArrayList<User>());
        
        //Followers of parent comment
        Comment c = comment.getParent();

        //Followers of the forum Entry
        ForumEntry fe = comment.getForumEntry();

        //Followers of the game
        Game g = comment.getForumEntry().getGame();

        //Find among users
        for (User u : userRepository.findAll()) {
            if(u.getComments().contains(c))
                matches.get(0).add(u);
            if(u.getForumEntries().contains(fe))
                matches.get(1).add(u);
            if(u.getGames().contains(g))
                matches.get(2).add(u);
                
        }

        return matches;
    }

}
