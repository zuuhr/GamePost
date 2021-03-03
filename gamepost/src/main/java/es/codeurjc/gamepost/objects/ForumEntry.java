package es.codeurjc.gamepost.objects;

import java.sql.Date;
import java.util.Dictionary;

public class ForumEntry extends ListElement{
    //#region Variables

    int id;
    String title;
    int authorId;
    Date createdOn;
    Date lastUpdatedOn;
    int votes;
    Content content;
    Dictionary<Integer, Comment> comments;

    //#endregion

    //#region Constructors

    public ForumEntry(int id, String title, int authorId, Date createdOn, Date lastUpdatedOn, int votes,
            Content content, Dictionary<Integer, Comment> comments) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    public Dictionary<Integer, Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment){
        comments.put(comment.id, comment);
    }

    /*
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    */

    //#endregion

    
    
}
