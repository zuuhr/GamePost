package es.codeurjc.gamepost.services;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

@Service
public class UserService {
    
    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private CustomListService customListService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Optional<User> get(String username){
        return userRepository.findByName(username);
    }

    public void submit(Model model, HttpServletRequest request, HttpSession session, String username, String password){
        User user = userRepository.save(new User(
                username, 
                passwordEncoder.encode(password),
                "ROLE_USER"
            ));

        logIn(model, request, session, user);
    }

    public boolean checkPassword(String username, String password){
        
        Optional<User> user = userRepository.findByName(username);

        if(user.isPresent()){
            String inputPassword = passwordEncoder.encode(password);
            log.info("INFO: Input password: " + inputPassword);
            boolean correct = user.get().getPassword().compareTo(inputPassword) == 0;
            log.info("INFO: Password is correct?: " + correct);
            return correct;
        }else{
            log.info("INFO: The user can not be found.");
            return false;
        }
    }

    //TODO: Check propper role assignment
    public void logIn(Model model, HttpServletRequest request, HttpSession session, User user){
        setRoleUserOrAdmin(model, request, user);

        session.setAttribute("username", user.getName());
        session.setAttribute("logged", true);
    }

    public void setRoleAnonymous(Model model, HttpServletRequest request){
        model.addAttribute("roleAnonymous", true);
        model.addAttribute("roleUser", false);
        model.addAttribute("user", null);
        model.addAttribute("roleAdmin", false);

        log.info("INFO: Role anonymous: " + model.getAttribute("roleAnonymous"));
        log.info("INFO: Role user: " + request.isUserInRole("ROLE_USER"));
        log.info("INFO: Role admin: " + request.isUserInRole("ROLE_ADMIN"));
    }

    public void setRoleUserOrAdmin(Model model, HttpServletRequest request, User user){
        model.addAttribute("roleAnonymous", false);
        model.addAttribute("roleUser", request.isUserInRole("ROLE_USER"));
        model.addAttribute("user", user);
        model.addAttribute("roleAdmin", request.isUserInRole("ROLE_ADMIN"));

        log.info("INFO: Role anonymous: " + model.getAttribute("roleAnonymous"));
        log.info("INFO: Role user: " + request.isUserInRole("ROLE_USER"));
        log.info("INFO: Role admin: " + request.isUserInRole("ROLE_ADMIN"));
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
