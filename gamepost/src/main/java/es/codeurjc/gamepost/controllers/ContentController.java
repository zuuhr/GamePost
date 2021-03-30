package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.services.SubmitElementService;

@Controller
public class ContentController {
    
    @Autowired
    private SubmitElementService submitElementService;

    //TODO: Associate this method with the form in the web
    @RequestMapping("/submit/Content")
    public String submitContent(@RequestParam String text, @RequestParam String media)
    {
        submitElementService.submitContent(text, media);

        return "index"; //TODO: Return a meaningfull html
    }
}
