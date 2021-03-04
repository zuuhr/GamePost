package es.codeurjc.gamepost.objects;

import java.util.ArrayList;
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

    @OneToOne(cascade=CascadeType.ALL) CustomList<ForumEntry> forumEntries;
    @OneToOne(cascade=CascadeType.ALL) CustomList<Comment> comments;
    @OneToOne(cascade=CascadeType.ALL) CustomList<Game> games;
    @OneToMany(cascade=CascadeType.ALL) List<Notification> notifications;

    @OneToOne(cascade=CascadeType.ALL) CustomList<ForumEntry> followingForumEntries;
    @OneToMany(cascade=CascadeType.ALL) List<CustomList<ListElement>> myLists;    

    public User(String name){
        this.name = name;

        this.forumEntries = new CustomList<ForumEntry>();
        this.comments = new CustomList<Comment>();
        this.games = new CustomList<Game>();
        this.notifications = new ArrayList<Notification>();

        this.followingForumEntries = new CustomList<ForumEntry>();
        this.myLists = new ArrayList<CustomList<ListElement>>();
    }
}
