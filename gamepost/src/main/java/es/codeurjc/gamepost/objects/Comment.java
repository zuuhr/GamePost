package es.codeurjc.gamepost.objects;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Comment extends ListElement{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    String title;
    @ManyToOne User author;
    @OneToOne(cascade=CascadeType.ALL) Content content;
    @OneToOne Comment parent;   //TODO: Define a strategy for root comments
    String[] media;     //TODO: Reference to a type URL? (Url would contain a string)
    
    public Comment(int id, String title, User author, Content content, Comment parent, String[] media) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.parent = parent;
        this.media = media;
    }

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

//    public int getVotes(){
//        return votes;
//    }
//
//    public void increaseVotes(){
//        votes++;
//    }
//
//    public void decreaseVotes(){
//        votes--;
//    }

    public String[] getMedia(){
        return media;
    }
    
    //#endregion
}
