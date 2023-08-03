package objects.questions;

public class QuestionResponse implements Question {

    private final String questionText;
    private final String correctAnswer;
    private int timer;

    public QuestionResponse(String questionText, String correctAnswer) {
        this.questionText = questionText.trim();
        this.correctAnswer = correctAnswer.trim().toLowerCase();
    }
    @Override
    public String getQuestion() {
        return questionText;
    }

    @Override
    public int evaluate(String answer) {
        return correctAnswer.equals(answer.trim().toLowerCase()) ? 1 : 0;
    }

    @Override
    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public int getTimer() {
        return timer;
    }

    @Override
    public int getNumFields() {
        return 1;
    }
}