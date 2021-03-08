package es.codeurjc.gamepost.objects;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//import java.sql.Blob;

import javax.persistence.Entity;

@Entity
public class Game extends ListElement{
    
    //#region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String cover;
    @OneToOne(cascade=CascadeType.ALL) Description description;
    @OneToOne(cascade=CascadeType.ALL) Forum forum;

    //#endregion

    //#region Constructor

    public Game(){}

    public Game(String cover, Description description){
        this.cover = cover;
        this.description = description;
        this.forum = new Forum();
    }

    //#endregion

    //#region

    public int getId() {
        return id;
    }

    public String getCover() {
        return cover;
    }

    public Description getDescription() {
        return description;
    }

    public Forum getForum() {
        return forum;
    }

    //#endregion
}
