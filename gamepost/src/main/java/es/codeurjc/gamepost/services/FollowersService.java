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
    UserRepository userRepository;
    
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
}
