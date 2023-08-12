package objects;

import java.sql.Timestamp;

public class Review {
    private int user_id;
    private int quiz_id;
    private String content;
    private Timestamp time;

    public Review(int user_id, int quiz_id, String content, Timestamp time) {
        this.user_id = user_id;
        this.quiz_id = quiz_id;
        this.content = content;
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }
}
