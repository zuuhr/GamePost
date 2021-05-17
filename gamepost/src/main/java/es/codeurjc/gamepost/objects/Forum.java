package es.codeurjc.gamepost.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Forum {

    //#region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER) List<ForumEntry> forumEntries;    
    Date lastUpdatedOn;

    //#endregion

    //#region Constructor
    
    public Forum(){
        forumEntries = new ArrayList<ForumEntry>();
        lastUpdatedOn = new Date();
    }

    //#endregion

    //#region Getters&Setters

    public boolean addForumEntry(ForumEntry forumEntry){
        return forumEntries.add(forumEntry);
    }

    public boolean removeForumEntry(ForumEntry forumEntry){
        return forumEntries.remove(forumEntry);
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        if (this.lastUpdatedOn.compareTo(lastUpdatedOn) < 0)
            this.lastUpdatedOn = lastUpdatedOn;
    }

    //#endregion
}
