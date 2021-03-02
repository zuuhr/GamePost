package es.codeurjc.gamepost.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(" / ")
    public String enlace(Model model){
        return "index";
    }
}
