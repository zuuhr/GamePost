package es.codeurjc.gamepost.objects;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    String name;
    String password; //hashed

    //@OneToOne(cascade=CascadeType.ALL) CustomList<ForumEntry> forumEntries;
    //@OneToOne(cascade=CascadeType.ALL) CustomList<Comment> comments;
    //@OneToOne(cascade=CascadeType.ALL) CustomList<Game> games;
    //@OneToOne(cascade=CascadeType.ALL) CustomList<ForumEntry> followingForumEntries;
    
    //@OneToMany(cascade=CascadeType.ALL) List<Notification> notifications;
    //@OneToMany(cascade=CascadeType.ALL) List<CustomList<ListElement>> myLists;    

    public User(String name){
        this.name = name;
    }
}
