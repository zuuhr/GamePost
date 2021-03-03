package es.codeurjc.gamepost.objects;

import java.util.Dictionary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;
    String password; //hashed
    CustomList<ForumEntry> forumEntries;
    CustomList<Comment> comments;
    CustomList<Game> games;
    CustomList<ForumEntry> followingForumEntries;
    Dictionary<Integer, Notification> notifications;
    Dictionary<Integer, CustomList<ListElement>> myLists;    

    public User(String name){
        this.name = name;
    }
}
