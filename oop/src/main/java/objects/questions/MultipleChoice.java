package objects.questions;

public class MultipleChoice implements Question {
    private final String questionText;
    private final String choices;
    private final String correctAnswer;
    private final int numFields;
    private int timer;

    public MultipleChoice(String questionText, String[] choices, String answer) {
        this.questionText = questionText.trim();
        this.correctAnswer = answer.trim().toLowerCase();
        this.choices = Parser.StringArrayToString(choices);
        this.numFields = choices.length;
    }
    @Override
    public String getQuestion() {
        return questionText + ":" + choices;
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
        return numFields;
    }
}
