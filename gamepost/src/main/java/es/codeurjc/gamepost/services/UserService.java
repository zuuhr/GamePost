package es.codeurjc.gamepost.services;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    
    @Cacheable
    public Optional<User> get(String username){
        log.info("INFO: Wanted username is " + username);
        Optional<User> user = userRepository.findByName(username);

        if(!user.isPresent())
            log.info("INFO: Wanted username is not present");

        return user;
    }

    @Cacheable
    public Optional<User> get(int id){
        log.info("INFO: Wanted username is id " + id);
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent())
            log.info("INFO: Wanted username is not present");

        return user;
    }

    public void submit(Model model, HttpServletRequest request, HttpSession session, String username, String password){
        User user = userRepository.save(new User(
                username, 
                passwordEncoder.encode(password),
                "ROLE_USER"
            ));

        //logIn(model, request, session, user);
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

        //session.setAttribute("username", user.getName());
        session.setAttribute("logged", true);

        //Load info
        loadInfo(model, session);
    }

    public void logOut(Model model, HttpServletRequest request, HttpSession session){
        setRoleAnonymous(model, request);

        //session.setAttribute("username", null);
        session.setAttribute("logged", false);

        return;
    }

    public void setRoleAnonymous(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        session.setAttribute("roleAnonymous", true);
        session.setAttribute("roleUser", false);
        session.setAttribute("user", null);
        session.setAttribute("username", null);
        session.setAttribute("roleAdmin", false);

        //log.info("INFO: Role anonymous: " + model.getAttribute("roleAnonymous"));
        //log.info("INFO: Role user: " + request.isUserInRole("ROLE_USER"));
        //log.info("INFO: Role admin: " + request.isUserInRole("ROLE_ADMIN"));
    }

    public void setRoleUserOrAdmin(Model model, HttpServletRequest request, User user){
        HttpSession session = request.getSession();

        session.setAttribute("roleAnonymous", false);
        session.setAttribute("roleUser", request.isUserInRole("ROLE_USER"));
        session.setAttribute("user", user.getId());
        session.setAttribute("username", user.getName());
        session.setAttribute("roleAdmin", request.isUserInRole("ROLE_ADMIN"));

        //log.info("INFO: Role anonymous: " + model.getAttribute("roleAnonymous"));
        //log.info("INFO: Role user: " + request.isUserInRole("ROLE_USER"));
        //log.info("INFO: User name: " + ((User) model.getAttribute("user")).getName());
        //log.info("INFO: Role admin: " + request.isUserInRole("ROLE_ADMIN"));
    }

    public void loadInfo(Model model, HttpSession session){
        Optional<User> user = get((int)session.getAttribute("user"));
        if (user.isPresent()){
            // Load the user info
            customListService.showIndex(model, session, user.get());
            notificationService.show(model, session, user.get());
        }
    }

    @Cacheable
    public User getSessionUser(HttpSession session){
        if((boolean) session.getAttribute("logged")){
            Optional<User> user = get((int)session.getAttribute("user"));
            if (user.isPresent()){
                return user.get();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
