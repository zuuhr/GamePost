package es.codeurjc.gamepost.objects;

public class Notification {
    String origin;
    boolean isRead;
    String title;

    public Notification(String origin, String title) {
        this.origin = origin;
        this.isRead = false;
        this.title = title;
    }

    public Notification(String origin, boolean isRead, String title) {
        this.origin = origin;
        this.isRead = isRead;
        this.title = title;
    }

    public void sendNotification(int user) throws Exception {
        throw new Exception("Not implemented yet.");
    }
}