package es.codeurjc.gamepost.objects;

import java.sql.Date;
import java.util.List;

public class Description {
    String name;
    List<Integer> genre;        //TODO: define enums
    int numPlayers;
    Date publishedOn;
    List<Integer> platform;     //TODO: define enums
    int developper;             //TODO: define enums
    int publisher;              //TODO: define enums
    //int gameEngine;
    String synopsis;
}
