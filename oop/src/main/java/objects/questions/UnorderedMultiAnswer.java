package objects.questions;

import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;

public class UnorderedMultiAnswer implements MultiAnswerType, Serializable {
    private final HashSet<String> correctAnswers;
    public UnorderedMultiAnswer(String[] answers) {
        this.correctAnswers = new HashSet();
        for (int i = 0; i < answers.length; i++) {
            this.correctAnswers.add(answers[i].trim().toLowerCase());
        }
    }
    @Override
    public int evaluate(String answer) {
        return EvaluateScoreForMultipleUnorderedAnswers.evaluate(correctAnswers, answer, false);
    }
}
