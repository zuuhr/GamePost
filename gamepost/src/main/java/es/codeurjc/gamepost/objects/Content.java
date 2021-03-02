package es.codeurjc.gamepost.objects;

public class Content {
    int id;
    String text;
    // Blob[] / String[] media; //TODO: Decide whether to store the url to the object in the databes or the object itself.

    //#region Constructor

    public Content(int id, String text) {
        this.id = id;
        this.text = text;
    }

    //#endregion

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    //#endregion
}
