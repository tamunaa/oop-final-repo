package objects.questions;

public class PictureResponse extends QuestionResponse {
    public PictureResponse(String URL, String correctAnswer) {
        super(URL, correctAnswer);
    }

    @Override
    public String getQuestionType() {
        return "PictureResponse";
    }
}
