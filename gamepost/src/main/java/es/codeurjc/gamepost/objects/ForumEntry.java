package es.codeurjc.gamepost.objects;

import java.beans.Transient;
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
    @ManyToOne Game game;
    Date createdOn;
    Date lastUpdatedOn;
    int votes;
    int replies;
    @OneToOne(cascade=CascadeType.ALL) Content content;
    @OneToMany(mappedBy = "forumEntry",cascade=CascadeType.ALL) List<Comment> comments;

    //#endregion

    //#region Constructors

    public ForumEntry(){}
    
    public ForumEntry(String title, User author, Game game, Content content) {
        this.title = title;
        this.author = author;
        this.game = game;
        this.createdOn = new Date();
        setLastUpdatedOn(new Date());
        this.votes = 0;
        this.replies = 0;
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

    public Game getGame(){
        return game;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        game.forum.setLastUpdatedOn(lastUpdatedOn);
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

    //TO DO: Upgrade this method by storing the childs and parents in Comment type instead of Integer.
    public List<Comment> getComments() {
        return comments;
    }

    public boolean addComment(Comment comment){
        lastUpdatedOn = new Date();

        //Insert comment
        Comment parent = comment.getParent();
        Boolean result = false;
                
        //Comment raíz
        if(parent == null){
            result = comments.add(comment);
        }

        //If the parent already is in the result List, insert the child after him.
        //TODO: This does not work. It seems it doesn't propagate the order to the database.
        else
        {
            int index = comments.indexOf(parent) + 1;
            if (index == comments.size()){
                result = comments.add(comment);
            }
            else
            {
                comments.add(index, comment);
                result = true;
            }
        }

        return result;
    }

    public boolean removeComment(Comment comment){
        return comments.remove(comment);
    }

    //#endregion

    //#region Methods

    //public List<Integer> sortCommentList(List<Integer> commentIdList){
    //    List<Integer> result = new ArrayList<Integer>();
//
    //    for (Integer commentId : commentIdList) {
    //        if(!result.contains(commentId)){
    //            result.add(commentId);
    //            List<Integer> subResult = sortCommentList(commentRepository.findById(commentId).get().getChilds());
    //            for (Integer comment2 : subResult) {
    //                result.add(comment2);
    //            }
    //        }
    //    }
//
    //    return result;
    //}

    //#endregion
}
