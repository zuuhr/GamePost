package es.codeurjc.gamepost.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Comment extends ListElement{
    
    //#region Variable
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    @ManyToOne User author;
    @OneToOne(cascade=CascadeType.ALL) Content content;
    Date postedOn;
    @ManyToOne ForumEntry forumEntry;
    
    @ManyToMany List<Comment> parent = new ArrayList<Comment>();   //TODO: Define a strategy for root comments
    @ManyToMany (mappedBy = "parent") List<Comment> childs;
    int childness;

    //#endregion

    //#region Constructor
    
    public Comment(){}
    
    public Comment(User author, ForumEntry forumEntry, Comment parent, Content content) {
        this.author = author;
        this.forumEntry = forumEntry;
        this.content = content;
        this.parent.add(parent);
        this.postedOn = new Date();

        if (parent!=null){
            this.childness = parent.getChildness() + 1;
            parent.addChild(this);
        }else{
            this.childness = 0;
        }
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

    public int getChildness(){
        return childness;
    }

    public boolean addChild(Comment child){
        return childs.add(child);
    }

    public Date getPostedOn(){
        return postedOn;
    }
    
    public ForumEntry getForumEntry() {
        return forumEntry;
    }
    
    //#endregion

}
