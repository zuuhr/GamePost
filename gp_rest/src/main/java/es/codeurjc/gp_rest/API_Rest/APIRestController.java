package es.codeurjc.gp_rest.API_Rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.gp_rest.objects.Description;
import es.codeurjc.gp_rest.objects.Game;
import es.codeurjc.gp_rest.objects.Notification;
import es.codeurjc.gp_rest.objects.User;
import es.codeurjc.gp_rest.objects.enums.Genre;
import es.codeurjc.gp_rest.repositories.DescriptionRepository;
import es.codeurjc.gp_rest.repositories.UserRepository;


@RestController
public class APIRestController {

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(APIRestController.class);
    @GetMapping("/indexByPreferences/{userId}")  // Because I want to get existing games
    public ResponseEntity<List<Game>> indexByPreferences(@PathVariable int userId){

        // Get user
        User user = userRepository.findById(userId).get();

        //Get inputs
        //Tener en cuenta genero y developers
        HashSet<Genre> genres = new HashSet<Genre>();
        HashSet<String> developers = new HashSet<String>();
        for (Game g : user.getGames().getAllElements()) {
            for (Genre genre : g.getDescription().getGenre()) {
                genres.add(genre);
            }

            developers.add(g.getDescription().getDevelopper());
        }

        //Operate
        HashMap<Game, Integer> result = new HashMap<Game, Integer>();
        for (Genre genre : genres) {
            List<Description> tmp = descriptionRepository.findByGenreContaining(genre);
            
            for (Description descr : tmp) {
                Game game = descr.getGame();
                if(result.containsKey(game)){
                    result.replace(game, result.get(game) + 1);
                }else{
                    result.put(game, 1);
                }
            }
        }

        for (String developer : developers) {
            List<Description> tmp = descriptionRepository.findByDeveloper(developer);
            
            for (Description descr : tmp) {
                Game game = descr.getGame();
                if(result.containsKey(game)){
                    result.replace(game, result.get(game) + 1);
                }else{
                    result.put(game, 1);
                }
            }
        }

        // Return the objects I want the client to receive
        //Sort them by number of coincidences
        
        List<Game> result_games = result.entrySet().stream()
            .sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue()))   //Order by int
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        log.info("INFO: IndexByPreferences Done");

        if(result_games.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(result_games, HttpStatus.OK);
    }

    @PostMapping("/sendNotifications")  // Because I'm going to create new notifications
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNotifications(@RequestBody Notification notification){
        return;
    }
}
