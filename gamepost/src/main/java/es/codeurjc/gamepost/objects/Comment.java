package es.codeurjc.gamepost.objects;

public class Comment extends ListElement{
    int id;
    String title;
    int authorId;
    Content content;
    int parentId;
    String[] media;
    
    public Comment(int id, String title, int authorId, Content content, int parentId, String[] media) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.parentId = parentId;
        this.media = media;
    }

    //#region Getters&Setters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public Content getContent() {
        return content;
    }

    public int getParentId() {
        return parentId;
    }

//    public int getVotes(){
//        return votes;
//    }
//
//    public void increaseVotes(){
//        votes++;
//    }
//
//    public void decreaseVotes(){
//        votes--;
//    }

    public String[] getMedia(){
        return media;
    }
    
    //#endregion
}
