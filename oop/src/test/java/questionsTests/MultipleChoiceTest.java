package questionsTests;

import objects.questions.MultipleChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipleChoiceTest {

    private MultipleChoice multipleChoice;

    @BeforeEach
    public void setUp() {
        String questionText = "What is the capital of France?";
        String[] choices = {"Paris", "Berlin", "Rome"};
        String correctAnswer = "Paris";
        multipleChoice = new MultipleChoice(questionText, choices, correctAnswer);
    }

    @Test
    public void testGetQuestion() {
        String expectedQuestion = "What is the capital of France?:Paris,Berlin,Rome";
        String actualQuestion = multipleChoice.getQuestion();
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    public void testEvaluateCorrectAnswer() {
        String answer = "Paris";
        int expectedScore = 1;
        int actualScore = multipleChoice.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateIncorrectAnswer() {
        String answer = "Berlin";
        int expectedScore = 0;
        int actualScore = multipleChoice.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateCaseInsensitive() {
        String answer = "  PaRIs    ";
        int expectedScore = 1;
        int actualScore = multipleChoice.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testSetAndGetTimer() {
        int timer = 30;
        multipleChoice.setTimer(timer);
        int actualTimer = multipleChoice.getTimer();
        assertEquals(timer, actualTimer, "Timer should be set and retrieved correctly");
    }

    @Test
    public void testGetNumFields() {
        int expectedNumFields = 3;
        int actualNumFields = multipleChoice.getNumFields();
        assertEquals(expectedNumFields, actualNumFields, "getNumFields should return the correct number of fields");
    }
}
