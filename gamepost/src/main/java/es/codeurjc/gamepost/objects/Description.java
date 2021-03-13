package es.codeurjc.gamepost.objects;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import es.codeurjc.gamepost.objects.enums.Developer;
import es.codeurjc.gamepost.objects.enums.Genre;
import es.codeurjc.gamepost.objects.enums.Platform;
import es.codeurjc.gamepost.objects.enums.Publisher;
import javassist.runtime.Desc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Description {
    //#region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToOne(mappedBy = "description")
    Game game;
    String name;
    @ManyToMany List<Genre> genre;
    int numPlayers;
    Date publishedOn;
    @ManyToMany List<Platform> platform; 
    String developer;   
    String publisher;      
    String synopsis;

    //#endregion

    //#region Constructor

    public Description(){}
    
    public Description(String name, List<Genre> genre, int numPlayers, Date publishedOn, List<Platform> platform,
        String developper, String publisher, String synopsis) {
        this.name = name;
        this.genre = genre;
        this.numPlayers = numPlayers;
        this.publishedOn = publishedOn;
        this.platform = platform;
        this.developer = developper;
        this.publisher = publisher;
        this.synopsis = synopsis;
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }

    public List<Platform> getPlatform() {
        return platform;
    }

    public void setPlatform(List<Platform> platform) {
        this.platform = platform;
    }

    public String getDevelopper() {
        return developer;
    }

    public void setDevelopper(String developper) {
        this.developer = developper;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //#endregion
}