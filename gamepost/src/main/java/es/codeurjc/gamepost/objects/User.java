package es.codeurjc.gamepost.objects;

import java.util.Dictionary;

public class User {
    int id;
    String name;
    String password; //hashed
    CustomList<ForumEntry> forumEntries;
    CustomList<Comment> comments;
    CustomList<Game> games;
    CustomList<ForumEntry> followingForumEntries;
    Dictionary<Integer, Notification> notifications;
    Dictionary<Integer, CustomList<ListElement>> myLists;    
}
