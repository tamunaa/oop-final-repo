package objects.questions;

public class PictureRespnose extends QuestionResponse {
    public PictureRespnose(String URL, String correctAnswer) {
        super(URL, correctAnswer);
    }

    @Override
    public String getQuestionType() {
        return "PictureResponse";
    }
}
