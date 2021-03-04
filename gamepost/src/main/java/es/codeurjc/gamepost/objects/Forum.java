package es.codeurjc.gamepost.objects;

import java.util.Dictionary;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

public class Forum {
    @OneToMany(cascade=CascadeType.ALL) Dictionary<Integer, ForumEntry> forumEntries;    

    public void sortBy(){
        //TODO: pues eso. Hacer el método sortBy.
    }
}
