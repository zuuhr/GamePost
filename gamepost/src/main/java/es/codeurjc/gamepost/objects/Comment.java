package es.codeurjc.gamepost.objects;

public class Comment {
    int id;
    String title;
    int authorId;
    Content content;
    int parentId;
    //int votes;    //TODO: Decide whether to include votes in comments or not.
    //Blob[] / String[] media; //TODO: Use string to reference the object on the database or store it.
    
    public Comment(int id, String title, int authorId, Content content, int parentId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.parentId = parentId;
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
    
    //#endregion
}
