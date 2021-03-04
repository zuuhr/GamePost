package es.codeurjc.gamepost.objects;

import java.sql.Date;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import es.codeurjc.gamepost.objects.enums.DevelopperEnum;
import es.codeurjc.gamepost.objects.enums.GenreEnum;
import es.codeurjc.gamepost.objects.enums.PlatformEnum;
import es.codeurjc.gamepost.objects.enums.PublisherEnum;

public class Description {
    String name;
    @ManyToMany List<GenreEnum> genre;        //TODO: define enums
    int numPlayers;
    Date publishedOn;
    @ManyToMany List<PlatformEnum> platform;     //TODO: define enums
    @ManyToOne DevelopperEnum developper;             //TODO: define enums
    @ManyToOne PublisherEnum publisher;              //TODO: define enums
    String synopsis;
}