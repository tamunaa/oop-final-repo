package objects.questions;

public interface Question {
    String getQuestion();
    int evaluate(String answer);
    void setTimer(int timer);
    int getTimer();
    int getNumFields();
//    String[] getChoices();
//    String[] correctAnswers();
//    boolean isOrdered();
}

// isOrdered
// getAnswers