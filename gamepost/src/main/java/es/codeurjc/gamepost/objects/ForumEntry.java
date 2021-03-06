package es.codeurjc.gamepost.objects;

import java.sql.Date;
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

    public ForumEntry(int id, String title, User author, Date createdOn, Date lastUpdatedOn, int votes,
            Content content, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdOn = createdOn;
        this.lastUpdatedOn = lastUpdatedOn;
        this.votes = votes;
        this.content = content;
        this.comments = comments;
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    public void addComment(Comment comment){
        comments.add(comment);
    }

    /*
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    */

    //#endregion
}
