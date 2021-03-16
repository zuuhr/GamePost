package es.codeurjc.gamepost.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import es.codeurjc.gamepost.repositories.CommentRepository;

import javax.persistence.Entity;

@Entity
public class Comment extends ListElement{
    
    //#region Variable
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    @ManyToOne User author;
    @OneToOne(cascade=CascadeType.ALL) Content content;
    
    @ManyToMany List<Comment> parent = new ArrayList<Comment>();   //TODO: Define a strategy for root comments
    @ManyToMany (mappedBy = "parent") List<Comment> childs;
    Date postedOn;

    //#endregion

    //#region Constructor
    
    public Comment(){}
    
    public Comment(User author, Comment parent, Content content) {
        this.author = author;
        this.content = content;
        this.parent.add(parent);
        this.postedOn = new Date();

        if (parent!=null)
            parent.addChild(this);
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

    //public int getParent() {
    //    return parent;
    //}

    public Comment getParent(){
        if(parent.size() != 0)
            return parent.get(0);
        else
            return null;
    }

    public List<Comment> getChilds(){
        return childs;
    }

    public boolean addChild(Comment child){
        return childs.add(child);
    }

    //#endregion
}
