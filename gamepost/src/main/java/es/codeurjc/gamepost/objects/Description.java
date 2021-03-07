package es.codeurjc.gamepost.objects;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
    
    String name;
    List<String> genre;
    int numPlayers;
    Date publishedOn;
    List<String> platform; 
    String developper;   
    String publisher;      
    String synopsis;

    //#endregion

    //#region Constructor

    public Description(){}
    
    public Description(String name, List<String> genre, int numPlayers, Date publishedOn, List<String> platform,
        String developper, String publisher, String synopsis) {
        this.name = name;
        this.genre = genre;
        this.numPlayers = numPlayers;
        this.publishedOn = publishedOn;
        this.platform = platform;
        this.developper = developper;
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

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
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

    public List<String> getPlatform() {
        return platform;
    }

    public void setPlatform(List<String> platform) {
        this.platform = platform;
    }

    public String getDevelopper() {
        return developper;
    }

    public void setDevelopper(String developper) {
        this.developper = developper;
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

    //#endregion
}