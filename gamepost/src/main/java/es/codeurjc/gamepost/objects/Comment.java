package es.codeurjc.gamepost.objects;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.Entity;

@Entity
public class Comment extends ListElement{
    
    //#region Variable
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    String title;
    @ManyToOne User author;
    @OneToOne(cascade=CascadeType.ALL) Content content;
    @OneToOne Comment parent;   //TODO: Define a strategy for root comments
    String[] media;     //TODO: Reference to a type URL? (Url would contain a string)

    //#endregion

    //#region Constructor
    
    public Comment(String title, User author, Content content, Comment parent, String[] media) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.parent = parent;
        this.media = media;
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public Comment getParent() {
        return parent;
    }

    public String[] getMedia(){
        return media;
    }
    
    //#endregion
}
