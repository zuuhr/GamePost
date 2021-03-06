package es.codeurjc.gamepost.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.Entity;

@Entity
public class ForumEntry extends ListElement{
    //#region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    String title;
    @ManyToOne User author;
    Date createdOn;
    Date lastUpdatedOn;
    int votes;
    @OneToOne(cascade=CascadeType.ALL) Content content;
    @OneToMany(cascade=CascadeType.ALL) List<Comment> comments;

    //#endregion

    //#region Constructors

    public ForumEntry(){}
    
    public ForumEntry(String title, User author, Date lastUpdatedOn, Content content) {
        this.title = title;
        this.author = author;
        this.createdOn = new Date();
        this.lastUpdatedOn = lastUpdatedOn;
        this.votes = 0;
        this.content = content;
        this.comments = new ArrayList<Comment>();
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public int getVotes() {
        return votes;
    }

    public void increaseVotes() {
        this.votes++;
    }

    public void decreaseVotes(){
        this.votes--;
    }

    public Content getContent() {
        return content;
    }

    /* What if the author wants to edit the post?
    public void setContent(Content content) {
        this.content = content;
    }
    */

    public List<Comment> getComments() {
        return comments;
    }

    public boolean addComment(Comment comment){
        return comments.add(comment);
    }

    public boolean removeComment(Comment comment){
        return comments.remove(comment);
    }

    //#endregion
}
