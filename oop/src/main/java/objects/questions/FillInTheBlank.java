package objects.questions;

public class FillInTheBlank extends QuestionResponse{
    public FillInTheBlank(String questionText, String correctAnswer) {
        super(questionText, correctAnswer);
    }

    @Override
    public String getQuestionType() {
        return "FillInTheBlank";
    }
}
