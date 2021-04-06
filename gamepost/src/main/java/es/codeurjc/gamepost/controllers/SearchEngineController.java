package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.services.GameService;

@Controller
public class SearchEngineController {
    
    @Autowired
    GameService gameService;

    @RequestMapping("/search")
    public String search(Model model, @RequestParam String searchText){
        
        gameService.search(model, searchText);
        
        return "searchresults";
    }
}
