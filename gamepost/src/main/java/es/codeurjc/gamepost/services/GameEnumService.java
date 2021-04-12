package es.codeurjc.gamepost.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.repositories.enums.GenreRepository;
import es.codeurjc.gamepost.repositories.enums.PlatformRepository;

@Service
public class GameEnumService {
    
    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    GenreRepository genreRepository;
    
    public List<Genre> getGenreList(
        String genreAction,
        String genreAdventure, 
        String genreFighting,
        String genreRolePlaying,
        String genreRacing,
        String genreSimulation,
        String genreSports,
        String genreStrategy,
        String genreVisualNovel
    ){
        List<Genre> genres = new ArrayList<Genre>();

        if(genreAction != null) genres.add(genreRepository.findByText("Action").get());
        if(genreAdventure != null) genres.add(genreRepository.findByText("Adventure").get());
        if(genreFighting != null) genres.add(genreRepository.findByText("Fighting").get());
        if(genreRolePlaying != null) genres.add(genreRepository.findByText("Role Playing").get());
        if(genreRacing != null) genres.add(genreRepository.findByText("Racing").get());
        if(genreSimulation != null) genres.add(genreRepository.findByText("Simulation").get());
        if(genreSports != null) genres.add(genreRepository.findByText("Sports").get());
        if(genreStrategy != null) genres.add(genreRepository.findByText("Strategy").get());
        if(genreVisualNovel != null) genres.add(genreRepository.findByText("Visual Novel").get());

        return genres;
    }

    public List<Platform> getPlatformList(
        String platformWindows,
        String platformMac,
        String platformLinux,
        String platformPS5,
        String platformPS4,
        String platformSwitch,
        String platformXboxSeriesXS,
        String platformAndroid,
        String platformIOS
    ){
        List<Platform> platforms = new ArrayList<Platform>();

        if(platformWindows != null) platforms.add(platformRepository.findByText("Windows").get());
        if(platformMac != null) platforms.add(platformRepository.findByText("Mac").get());
        if(platformLinux != null) platforms.add(platformRepository.findByText("Linux").get());
        if(platformPS5 != null) platforms.add(platformRepository.findByText("Playstation 5").get());
        if(platformPS4 != null) platforms.add(platformRepository.findByText("Playstation 4").get());
        if(platformSwitch != null) platforms.add(platformRepository.findByText("Nintendo Switch").get());
        if(platformXboxSeriesXS != null) platforms.add(platformRepository.findByText("Xbox Series X/S").get());
        if(platformAndroid != null) platforms.add(platformRepository.findByText("Android").get());
        if(platformIOS != null) platforms.add(platformRepository.findByText("IOS").get());

        return platforms;
    }
}
