package es.codeurjc.gamepost.services;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.servlet
        .support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.ListElement;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.GameRepository;

public class GameService {
    
    //#region Dependencies

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ForumEntryRepository forumEntryRepository;

    //#endregion

    public void submit(String titleText, MultipartFile coverFile, String playersText, String developerText, 
        String releaseText, String publisherText, String descriptionText){

        //Description
        List<Genre> genres = new ArrayList<Genre>();
        int numPlayers = Integer.parseInt(playersText);
        List<Platform> platforms = new ArrayList<Platform>();
        Date releaseDate;
        try {
            releaseDate = new SimpleDateFormat("dd/MM/yyyy").parse(releaseText);
            Description d = new Description(titleText, genres, numPlayers, releaseDate, platforms, developerText, publisherText, descriptionText);           
            Game game = new Game("", d);
            game.setCoverFile(BlobProxy.generateProxy(coverFile.getInputStream(), coverFile.getSize()));
            
            URI location = fromCurrentRequest().build().toUri();
            game.setCover(location.toString());
            ResponseEntity.created(location).build();
        
            gameRepository.save(game);

        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean get(Model model, int id){
        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
                model.addAttribute("game", game.get());
                model.addAttribute("description", game.get().getDescription());

                List<ForumEntry> posts = forumEntryRepository.findByGame(game.get());
                model.addAttribute("posts", posts);
                // TODO: model.addAttribute("user", user);
                
                return true;
        } else {
                return false;
        }
    }
}
