package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.services.ModelService;
import es.codeurjc.gamepost.services.UserService;

import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @Autowired
    private ModelService modelService;

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

    @PostMapping("/signin")
    public String signIn(Model model, HttpServletRequest request, HttpSession session, @RequestParam String username, @RequestParam String password){
        Optional<User> user = userService.get(username);
        if(user.isPresent()){
           //model.repeatedUser = true --> Displays a message in the Sign in page "The name is not available." 
           return "signin";
        }else{
            userService.submit(model, request, session, username, password);
            modelService.updateModel(model, session);
            return "redirect:/";
        }
    }

    /*
    @PostMapping("/login")
    public String logIn(Model model, HttpServletRequest request, HttpSession session, @RequestParam String username, @RequestParam String password){
        
        if(userService.checkPassword(username, password)){
            log.info("INFO: User logged.");
            userService.logIn(model, request, session, userService.get(username).get());
            return "redirect:/";
        }
        else{
            log.info("INFO: Wrong password.");
            return "login";
        }        
    }
    */

    @GetMapping("/loginSuccess")
    public String logInSuccess(Model model, Authentication auth, HttpServletRequest request, HttpSession session){
        userService.logIn(model, request, session, userService.get(auth.getName()).get());
        modelService.updateModel(model, session);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logOut(Model model, HttpServletRequest request, HttpSession session){
        
        userService.logOut(model, request, session);
        modelService.updateModel(model, session);
        //session.invalidate();
        
        return "redirect:/";
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