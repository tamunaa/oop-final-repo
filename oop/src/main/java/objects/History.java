package objects;

import java.security.Timestamp;

public class History {
    private int id;
    private int quizId;
    private int userId;
    private int score;
    private int timeRelapsed;
    private Timestamp dateTaken;

    public History(int quizId, int userId, int score, int timeRelapsed, Timestamp dateTaken) {
        this.quizId = quizId;
        this.userId = userId;
        this.score = score;
        this.timeRelapsed = timeRelapsed;
        this.dateTaken = dateTaken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public int getTimeElapsed() {
        return timeRelapsed;
    }

    public Timestamp getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Timestamp dateTaken) {
        this.dateTaken = dateTaken;
    }
}
