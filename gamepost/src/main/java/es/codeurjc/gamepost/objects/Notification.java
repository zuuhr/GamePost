package es.codeurjc.gamepost.objects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification implements Serializable{
    
    //#region Variables
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    int id;

    String origin;
    boolean isRead;
    String title;

    //#endregion

    //#region Constructor

    public Notification(){}
    
    public Notification(String origin, String title) {
        this.origin = origin;
        this.isRead = false;
        this.title = title;
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //#endregion
}
