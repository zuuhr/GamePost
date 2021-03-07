package es.codeurjc.gamepost.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.gamepost.repositories.ForumRepository;

@Controller
public class ForumPageController {
    @Autowired
    private ForumRepository forumRepository;

    @PostConstruct
    public void init(){
    }
}
