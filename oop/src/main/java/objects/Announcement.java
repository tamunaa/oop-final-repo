package objects;

import java.sql.Timestamp;

public class Announcement {
    private final int id;
    private final String title;
    private final int creatorId;
    private final Timestamp creationDate;
    private final String text;


    public Announcement(int id, int creatorId, Timestamp creationDate, String title, String text) {
        this.id = id;
        this.creatorId = creatorId;
        this.creationDate = creationDate;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }
    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
