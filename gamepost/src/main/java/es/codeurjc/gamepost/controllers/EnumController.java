package es.codeurjc.gamepost.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.codeurjc.gamepost.objects.enums.Developer;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.objects.enums.Publisher;
import es.codeurjc.gamepost.repositories.enums.DeveloperRepository;
import es.codeurjc.gamepost.repositories.enums.GenreRepository;
import es.codeurjc.gamepost.repositories.enums.PlatformRepository;
import es.codeurjc.gamepost.repositories.enums.PublisherRepository;

@Controller
public class EnumController {
    @Autowired
    GenreRepository genreRepository;
    
    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @PostConstruct
    public void init(){
        genreRepository.save(new Genre("Adventure"));

        platformRepository.save(new Platform("Switch"));

        developerRepository.save(new Developer("Nintendo"));

        publisherRepository.save(new Publisher("Nintendo"));
    }
}
