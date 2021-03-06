package es.codeurjc.gamepost.objects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Forum {

    //#region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToMany(cascade=CascadeType.ALL) List<ForumEntry> forumEntries;    

    //#endregion

    //#region Constructor
    
    public Forum(){
        forumEntries = new ArrayList<ForumEntry>();
    }

    //#endregion

    //#region Getters&Setters

    public boolean addForumEntry(ForumEntry forumEntry){
        return forumEntries.add(forumEntry);
    }

    public boolean removeForumEntry(ForumEntry forumEntry){
        return forumEntries.remove(forumEntry);
    }

    //#endregion

    //#region Methods

    public void sortBy(){
        //TODO: pues eso. Hacer el método sortBy.
    }

    //#endregion
}
