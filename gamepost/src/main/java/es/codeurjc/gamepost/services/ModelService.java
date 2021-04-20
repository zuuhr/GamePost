package es.codeurjc.gamepost.services;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ModelService {
    
    private Logger log = LoggerFactory.getLogger(ModelService.class);
    public void updateModel(Model model, HttpSession session){
        
        model.addAttribute("logged", session.getAttribute("logged"));
        model.addAttribute("roleAnonymous", session.getAttribute("roleAnonymous"));
        model.addAttribute("roleUser", session.getAttribute("roleUser"));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("roleAdmin", session.getAttribute("roleAdmin"));

        model.addAttribute("notification", session.getAttribute("notification"));
        model.addAttribute("list", session.getAttribute("list"));
        model.addAttribute("customlist", session.getAttribute("customlist"));
        model.addAttribute("customforumentrylist", session.getAttribute("customforumentrylist"));
        model.addAttribute("customcommentlist", session.getAttribute("customcommentlist"));

        model.addAttribute("latestposts", session.getAttribute("latestposts"));

        //model.addAttribute("games", session.getAttribute("games"));
        //model.addAttribute("searchText", session.getAttribute("searchText"));        

        //model.addAttribute("game", session.getAttribute("game"));
        //model.addAttribute("description", session.getAttribute("description"));

        //model.addAttribute("posts", session.getAttribute("posts"));
        //model.addAttribute("comments", session.getAttribute("comments"));
        //model.addAttribute("forumentry", session.getAttribute("forumentry"));

        log.info("INFO: UpdateModel: Model -> " + model.toString());
        return;
    }
}
