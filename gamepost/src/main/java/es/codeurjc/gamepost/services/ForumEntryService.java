package es.codeurjc.gamepost.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;

@Service
public class ForumEntryService {
    
    @Autowired
    ForumEntryRepository forumEntryRepository;
    
    public ForumEntry get(int id){
        Optional<ForumEntry> fe = forumEntryRepository.findById(id);
        
        if(fe.isPresent())
            return fe.get();
        else
            return null;
    }
}
