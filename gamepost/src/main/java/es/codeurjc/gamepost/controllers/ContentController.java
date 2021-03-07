package es.codeurjc.gamepost.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.repositories.ContentRepository;

@Controller
public class ContentController {
    
    @Autowired
    private ContentRepository contentRepository;

    @PostConstruct
    public void init(){
        contentRepository.save(new Content("Vaya juegazo", null));
        contentRepository.save(new Content("No me gusta", null));
        contentRepository.save(new Content("Me encanta", null));
        contentRepository.save(new Content("Wow amazing", null));
        contentRepository.save(new Content("suka blyat", null));
        contentRepository.save(new Content("loooool goty", null));
        contentRepository.save(new Content("omg", null));
    }

    //TODO: Associate this method with the form in the web
    @RequestMapping("/submit/Content")
    public String submitContent(@RequestParam String text, @RequestParam String[] media)
    {
        contentRepository.save(new Content(text, media));

        return "index"; //TODO: Return a meaningfull html
    }
}
