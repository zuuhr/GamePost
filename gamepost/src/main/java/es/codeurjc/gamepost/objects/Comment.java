package es.codeurjc.gamepost.objects;

import java.util.Date;

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
    
    @ManyToOne User author;
    @OneToOne(cascade=CascadeType.ALL) Content content;
    int parent;   //TODO: Define a strategy for root comments
    Date postedOn;
    //#endregion

    //#region Constructor
    
    public Comment(){}
    
    public Comment(User author, Content content, int parent) {
        this.author = author;
        this.content = content;
        this.parent = parent;
        this.postedOn = new Date();
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public int getParent() {
        return parent;
    }
    
    //#endregion
}
