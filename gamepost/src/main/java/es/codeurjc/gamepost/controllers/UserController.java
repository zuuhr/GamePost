package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.services.UserService;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    /*
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(users);
        }
    }*/

    @RequestMapping("/signIn")
    public String signIn(Model model, HttpSession session, @RequestParam String username, @RequestParam String password){
        Optional<User> user = userService.get(username);
        if(user.isPresent()){
           //model.repeatedUser = true --> Displays a message in the Sign in page "The name is not available." 
           return "signin";
        }else{
            userService.submit(session, username, password);
            return "redirect:/";
        }
    }

    @RequestMapping("/logIn")
    public String logIn(Model model, HttpSession session, @RequestParam String username, @RequestParam String password){
        Optional<User> user = userService.get(username);
        if(user.isPresent()){
            if(user.get().getPassword().compareTo(password) == 0){
                log.info("INFO: User logged.");
                userService.logIn(session, user.get());
                return "redirect:/";
            }else{
                log.info("INFO: Wrong password.");
                return "login";
            }
        }else{
            log.info("INFO: The user can not be found.");
            return "login";
        }
    }

    /*
    @GetMapping("/notifications")
    public String showNotifications(Model model){
        //User user = (User) model.getAttribute("user");
        User user = userRepository.findByName("Mariam").get();
        
        model.addAttribute("notifications", user.getNotifications());

        //Debug
        log.info("--NOTIFICATIONS--");
        for (Notification notification : user.getNotifications()) {
            log.info("Notification " + notification.getId() + ": " + notification.getTitle());
        }

        //TO DO: Return a meaningfull page that shows the notifications.
        return "notifications";
    }*/
}