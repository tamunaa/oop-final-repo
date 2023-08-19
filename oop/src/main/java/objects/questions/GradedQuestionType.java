package objects.questions;

public interface GradedQuestionType {
    String getQuestion();
    void setTimer(int timer);
    int getTimer();
    void setAnswer(String answer);
    String getAnswer();
    String getQuestionType();
    int getQuestionId();
    void setQuestionId(int questionId);
}
