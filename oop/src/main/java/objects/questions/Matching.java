package objects.questions;

import java.util.*;

public class Matching implements Question {
    private final String questionText;
    private final String questions;
    private final String choices;
    private final HashMap<String, String> correctAnswer;
    private final int numFields;
    private int timer;

    public Matching(String questionText, String[] questions, String[] choices) {
        this.questionText = questionText;
        this.numFields = questions.length;
        this.correctAnswer = new HashMap<>();
        for (int i = 0; i < questions.length; i++) {
            correctAnswer.put(questions[i].trim().toLowerCase(), choices[i].trim().toLowerCase());
        }
        Collections.shuffle(Arrays.asList(choices));
        this.choices = Parser.StringArrayToString(choices);
        this.questions = Parser.StringArrayToString(questions);
    }
    @Override
    public String getQuestion() {
        return questions + ":" + choices;
    }

    @Override
    public int evaluate(String answer) {
        int score = 0;
        HashMap<String, String> ans = Parser.GetQuestionsAndAnswersOfMatching(answer);
        for (Map.Entry<String, String> entry : ans.entrySet()) {
            if (correctAnswer.get(entry.getKey().trim().toLowerCase()).equals(entry.getValue().trim().toLowerCase())) {
                score++;
            }
        }
        return score;
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
