package es.codeurjc.gamepost.objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
public class Content {
    //#region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String text;
    String media; //TODO: Reference to a type URL? (Url would contain a string)
                    //How do we get the link to access the resource on the database?
                    //TODO:Enable multiple media

    //#endregion
    
    //#region Constructor

    public Content(){}
    
    public Content(String text, String media) {
        this.text = text;
        this.media = media;
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMedia(){
        return media;
    }

    public void setMedia(String media){
        this.media = media;
    }
    
    //#endregion
}
