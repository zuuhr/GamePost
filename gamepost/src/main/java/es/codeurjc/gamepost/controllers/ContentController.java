package es.codeurjc.gamepost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.services.ContentService;

@Controller
public class ContentController {
    
    @Autowired
    private ContentService contentService;

    //TODO: Associate this method with the form in the web
    @PostMapping("/submit/Content")
    public String submitContent(@RequestParam String text, @RequestParam String media)
    {
        contentService.submit(text, media);

        return "index"; //TODO: Return a meaningfull html
    }
}
