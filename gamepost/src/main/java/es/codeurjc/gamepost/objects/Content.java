package es.codeurjc.gamepost.objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String text;
    String[] media; //TODO: Reference to a type URL? (Url would contain a string)
                    //How do we get the link to access the resource on the database?

    //#region Constructor

    public Content(int id, String text, String[] media) {
        this.id = id;
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

    public String[] getMedia(){
        return media;
    }

    public void setMedia(String[] media){
        this.media = media;
    }
    
    //#endregion
}
