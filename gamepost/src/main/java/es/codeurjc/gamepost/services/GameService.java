package es.codeurjc.gamepost.services;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.hibernate.engine.jdbc.BlobProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.servlet
        .support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.repositories.DescriptionRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.GameRepository;

@Service
public class GameService {
    
    //#region Dependencies

    @Autowired
    GameRepository gameRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    ForumEntryRepository forumEntryRepository;

    //#endregion

    public void submit(String titleText, List<Genre> genres, List<Platform> platforms, MultipartFile coverFile, String playersText, String developerText, 
        String releaseText, String publisherText, String descriptionText){

        //Description
        int numPlayers = Integer.parseInt(playersText);
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

    public boolean get(Model model, HttpSession session, int id){
        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            //session.setAttribute("game", game.get());
            //session.setAttribute("description", game.get().getDescription());
            
            model.addAttribute("game", game.get());
            model.addAttribute("description", game.get().getDescription());

            List<ForumEntry> posts = forumEntryRepository.findByGame(game.get());
            //session.setAttribute("posts", posts);
            model.addAttribute("posts", posts);
                
            return true;
        } else {
            return false;
        }
    }

    public void showIndexLatestUpdatedGames(Model model, HttpSession session){
        List<Game> games = gameRepository.findTop20ByOrderByForumLastUpdatedOnDesc();
        //session.setAttribute("games", games);
        model.addAttribute("games", games);

        return;
    }

    private Logger log = LoggerFactory.getLogger(GameService.class);
    public void showIndexGamesUserPreferences(Model model, HttpSession session, User user){
        //log.info("INFO: User id is " + user.getId());
        
        //Get games from API Rest
        RestTemplate restTemplate = new RestTemplate();
        //String url = "https://localhost:8443/indexByPreferences/" + user.getId();  //TODO: Watch the url match the port correctly
        String url = "http://localhost:8081/indexByPreferences/" + user.getId();

        try{
            //GamesResponse data = restTemplate.getForObject(url, GamesResponse.class);
            
            //ObjectNode data = restTemplate.getForObject(url, ObjectNode.class);
            
            //String data = restTemplate.getForObject(url, String.class);
            
            ArrayNode resources = restTemplate.getForObject(url, ArrayNode.class);
            ArrayNode items = resources;

            //session.setAttribute("games", data.items);
            
            List<Game> games = new ArrayList<Game>();
            //ArrayNode items = (ArrayNode) data.get("items");
            
            for(int i = 0; i< items.size(); i++){
                JsonNode item = items.get(i);
                int gameId = item.get("id").asInt();
                games.add(gameRepository.findById(gameId).get());
            }
            

            //for (Integer gameId : data.get("items")) {
            //    games.add(gameRepository.findById(gameId).get());
            //}

            model.addAttribute("games", games);

            //showIndexLatestUpdatedGames(model, session);
        }catch(HttpClientErrorException notFound){
            showIndexLatestUpdatedGames(model, session);
        }catch(InternalServerError internal){
            log.info("INFO: GameService: showIndexGamesUserPreferences. -> Internal Server Error.");
            showIndexLatestUpdatedGames(model, session);
        }

        return;
    }

    public void search(Model model, HttpSession session, String searchText){
        
        String[] words = searchText.split(" "); 
        List<Description> descriptions = descriptionRepository.findByNameInKeywords(words);
        if(descriptions.size() > 0){
            List<Game> games = new ArrayList<Game>();
            for (Description description : descriptions) {
                games.add(description.getGame());
            }
            //session.setAttribute("games", games);
            model.addAttribute("games", games);
        }

        //session.setAttribute("searchText", searchText);
        model.addAttribute("searchText", searchText);

        return;
    }
}
