package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CustomListRepository;

@Controller
public class CustomListController {
    
    @Autowired
    CustomListRepository customListRepository;

    //TODO: Associate this method with the form in the web
    @RequestMapping("/submit/CustomList")
    public String submitCustomList(Model model, @RequestParam String name){
        
        User author = (User) model.getAttribute("user");   //TODO: Coger user de sesión
        
        CustomList cl = customListRepository.save(new CustomList<>(name));
        author.addMyList(cl);

        return "index"; //TODO: Return a meaningfull html
    }
}
