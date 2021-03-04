package es.codeurjc.gamepost.objects;

import java.sql.Date;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import es.codeurjc.gamepost.objects.enums.Developper;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.objects.enums.Publisher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    String name;
    @ManyToMany List<Genre> genre;
    int numPlayers;
    Date publishedOn;
    @ManyToMany List<Platform> platform; 
    @ManyToOne Developper developper;   
    @ManyToOne Publisher publisher;      
    String synopsis;
}