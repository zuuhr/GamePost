package es.codeurjc.gamepost.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    CustomListService customListService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserRepository userRepository;
    
    public Optional<User> get(String username){
        return userRepository.findByName(username);
    }

    public void submit(HttpSession session, String username, String password){
        User user = userRepository.save(new User(
                username, 
                //new BCryptPasswordEncoder().encode(password));
                password
            ));

        logIn(session, user);
    }

    public void logIn(HttpSession session, User user){
        session.setAttribute("username", user.getName());
        session.setAttribute("logged", true);
    }

    public void loadInfo(Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        if (! (boolean) session.getAttribute("logged")){
        //if (session.isNew()){
                
        }else{
                // Load the user info
                Optional<User> user = userRepository.findByName(username);
                if (user.isPresent()){
                        customListService.showIndex(model, user.get());
                        notificationService.show(model, user.get());
                }
        }
    }

    public User getSessionUser(HttpSession session){
        String username = (String) session.getAttribute("username");
        if (session.isNew() || ! (boolean) session.getAttribute("logged")){
                return null;
        }else{
                // Load the user info
                Optional<User> user = userRepository.findByName(username);
                if (user.isPresent()){
                        return user.get();
                }else{
                    return null;
                }
        }
    }
}
