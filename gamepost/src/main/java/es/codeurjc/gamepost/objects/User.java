package es.codeurjc.gamepost.objects;

public class User {
    int id;
    String name;
    String password; //hashed
    CustomList<ForumEntry> forumEntries;
    CustomList<Comment> comments;
    CustomList<Game> games;
    CustomList<ForumEntry> followingForumEntries;
    CustomList<Notification> notifications;

    //TODO: Create generic element from which inherit
    //List<CustomList<T>> myLists;    
}
