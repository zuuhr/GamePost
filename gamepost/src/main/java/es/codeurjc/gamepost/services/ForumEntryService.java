package es.codeurjc.gamepost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.Notification;
import es.codeurjc.gamepost.objects.User;
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
    FollowersService followersService;

    @Autowired
    NotificationService notificationService;
    
    public ForumEntry get(int id){
        Optional<ForumEntry> fe = forumEntryRepository.findById(id);
        
        if(fe.isPresent())
            return fe.get();
        else
            return null;
    }

    public ForumEntry submit(int gameid, String titleText, String bodyText){
        
        //TODO: pick user from session
        User users_session = userRepository.findByName("Mariam").get();
        Content content = new Content(bodyText, "");
        Game game = gameRepository.findById(gameid).get();
        
        ForumEntry fe = forumEntryRepository.save(new ForumEntry(titleText, users_session, game, content));

        // TODO: Send a notification to all the users that follow this game
        notificationService.sendNotificationToFollowers(gameid, fe);

        return fe;
    }
}
