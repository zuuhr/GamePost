package es.codeurjc.gamepost.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.repositories.DescriptionRepository;
import es.codeurjc.gamepost.repositories.GameRepository;

@Controller
public class SearchEngineController {
    
    @Autowired
    GameRepository gameRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    @RequestMapping("/search")
    public String search(Model model, @RequestParam String searchText){
        
        List<Description> descriptions = descriptionRepository.findByName(searchText);
        if(descriptions.size() > 0){
            List<Game> games = new ArrayList<Game>();
            for (Description description : descriptions) {
                games.add(description.getGame());
                
            }

            model.addAttribute("games", games);
        }
        model.addAttribute("searchText", searchText);
        return "searchresults";
    }
}
