package es.codeurjc.gamepost.objects;

public class Content {
    int id;
    String text;
    String[] media;

    //#region Constructor

    public Content(int id, String text, String[] media) {
        this.id = id;
        this.text = text;
        this.media = media;
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

    public String[] getMedia(){
        return media;
    }

    public void setMedia(String[] media){
        this.media = media;
    }
    
    //#endregion
}
