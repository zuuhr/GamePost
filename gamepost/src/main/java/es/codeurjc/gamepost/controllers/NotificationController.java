package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Notification;
import es.codeurjc.gamepost.repositories.NotificationRepository;

@Controller
public class NotificationController {
    
    @Autowired
    NotificationRepository notificationRepository;

    //TODO: Associate this method with the form in the web
    @PostMapping("/submit/Notification")
    public String submitNotification(Model model, @RequestParam String title, 
        @RequestParam String origin)
        {        
        notificationRepository.save(new Notification(title, origin));

        return "index"; //TODO: Return a meaningfull html
    }

    
}
