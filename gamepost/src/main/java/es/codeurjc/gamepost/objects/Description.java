package es.codeurjc.gamepost.objects;

import java.sql.Date;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import es.codeurjc.gamepost.objects.enums.DevelopperEnum;
import es.codeurjc.gamepost.objects.enums.GenreEnum;
import es.codeurjc.gamepost.objects.enums.PlatformEnum;
import es.codeurjc.gamepost.objects.enums.PublisherEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    //TODO: Fix the Enums problem
    /*
    String name;
    @ManyToMany List<GenreEnum> genre;       
    int numPlayers;
    Date publishedOn;
    @ManyToMany List<PlatformEnum> platform; 
    @ManyToOne DevelopperEnum developper;   
    @ManyToOne PublisherEnum publisher;      
    String synopsis;
    */

    String name;
    int genre;       
    int numPlayers;
    Date publishedOn;
    int platform;    
    int developper;  
    int publisher;   
    String synopsis;
}