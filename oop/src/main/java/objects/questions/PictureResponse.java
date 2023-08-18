package objects.questions;

import java.io.Serializable;

public class PictureResponse extends QuestionResponse implements Serializable {
    public PictureResponse(String URL, String correctAnswer) {
        super(URL, correctAnswer);
    }

    @Override
    public String getQuestionType() {
        return "PictureResponse";
    }
}
