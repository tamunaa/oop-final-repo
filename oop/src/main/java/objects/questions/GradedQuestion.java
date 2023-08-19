package objects.questions;

public class GradedQuestion implements GradedQuestionType{
    private final String questionText;
    private int timer = 0;
    private int questionId = -1;
    private String answer;

    public GradedQuestion(String questionText){
        this.questionText = questionText;

    }
    @Override
    public String getQuestion() {
        return questionText;
    }

    @Override
    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public int getTimer() {
        return this.timer;
    }


    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return this.answer;
    }

    @Override
    public String getQuestionType() {
        return "Graded";
    }

    @Override
    public int getQuestionId() {
        return this.questionId;
    }

    @Override
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
