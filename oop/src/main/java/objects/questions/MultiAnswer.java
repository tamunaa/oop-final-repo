package objects.questions;

public class MultiAnswer implements Question {
    private final String questionText;
    private final int numFields;
    private final MultiAnswerType questionType;
    private int timer;

    public 	MultiAnswer(String questionText, String answers[], int numFields, boolean isOrdered) {
        this.questionText = questionText;
        this.numFields = numFields;
        this.questionType = isOrdered ? new OrderedMultiAnswer(answers) : new UnorderedMultiAnswer(answers);
    }

    @Override
    public String getQuestion() {
        return questionText;
    }

    @Override
    public int evaluate(String answer) {
        return questionType.evaluate(answer);
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
