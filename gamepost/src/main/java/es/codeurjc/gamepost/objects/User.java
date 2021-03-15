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
    //#region Variables

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
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL) List<CustomList<ListElement>> myLists;    

    //#endregion

    //#region Constructor

    public User() {}

    public User(String name, String password){
        this.name = name;
        this.password = password;

        this.forumEntries = new CustomList<ForumEntry>("ForumEntries");
        this.comments = new CustomList<Comment>("Comments");
        this.games = new CustomList<Game>("Games");
        this.notifications = new ArrayList<Notification>();

        this.followingForumEntries = new CustomList<ForumEntry>("Following ForumEntries");
        this.myLists = new ArrayList<CustomList<ListElement>>();
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;                //TODO: Store the password hashed.
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomList<ForumEntry> getForumEntries() {
        return forumEntries;
    }

    public CustomList<Comment> getComments() {
        return comments;
    }

    public CustomList<Game> getGames() {
        return games;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public CustomList<ForumEntry> getFollowingForumEntries() {
        return followingForumEntries;
    }

    public List<CustomList<ListElement>> getMyLists() {
        return myLists;
    }

    //#endregion

    //#region Object management Methods

    public boolean addNotification(Notification notification) {
        return this.notifications.add(notification);
    }

    public Notification getNotification(int index){
        return this.notifications.get(index);
    }

    public boolean removeNotification(Notification notification){
        return this.notifications.remove(notification);
    }

    public boolean addMyList(CustomList<ListElement> myList) {
        return this.myLists.add(myList);
    }

    public CustomList<ListElement> getMyList(int index){
        return this.myLists.get(index);
    }

    public boolean removeMyList(CustomList<ListElement> myList){
        return this.myLists.remove(myList);
    }

    //#endregion
}
