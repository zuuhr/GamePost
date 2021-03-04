package es.codeurjc.gamepost.objects;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToMany(cascade=CascadeType.ALL) List<ForumEntry> forumEntries;    

    public void sortBy(){
        //TODO: pues eso. Hacer el método sortBy.
    }
}
