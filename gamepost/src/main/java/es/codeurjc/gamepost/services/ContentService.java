package es.codeurjc.gamepost.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import es.codeurjc.gamepost.objects.Content;
import es.codeurjc.gamepost.repositories.ContentRepository;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public Content submitContent(String text, String media){
        return contentRepository.save(new Content(text, media));
    }
}
