package es.codeurjc.gamepost.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class User implements Serializable{
    //#region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    String name;
    String password;
    private String role;

    @OneToOne(cascade=CascadeType.ALL) CustomList<ForumEntry> forumEntries;
    @OneToOne(cascade=CascadeType.ALL) CustomList<Comment> comments;
    @OneToOne(cascade=CascadeType.ALL) CustomList<Game> games;
    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER) List<Notification> notifications;
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.EAGER) List<CustomList<ListElement>> myLists;    

    //#endregion

    //#region Constructor

    public User() {}

    public User(String name, String password, String role){
        this.name = name;
        this.password = password;
        this.role = role;
        
        this.forumEntries = new CustomList<ForumEntry>("[ForumEntries]", this);
        this.comments = new CustomList<Comment>("[Comments]", this);
        this.games = new CustomList<Game>("[Games]", this);
        this.notifications = new ArrayList<Notification>();
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

    public String getRole(){
        return role;
    }

    public CustomList<ForumEntry> getForumEntries() {
        return forumEntries;
    }

    public void followForumEntry(ForumEntry fe){
        forumEntries.addElement(fe);
    }

    public CustomList<Comment> getComments() {
        return comments;
    }

    public void followComment(Comment c){
        comments.addElement(c);
    }

    public CustomList<Game> getGames() {
        return games;
    }

    public void followGame(Game game){
        games.addElement(game);
    }

    public List<Notification> getNotifications() {
        return notifications;
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
