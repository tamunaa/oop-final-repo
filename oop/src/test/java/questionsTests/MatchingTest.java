package questionsTests;

import objects.questions.Matching;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchingTest {

    private Matching matching;

    @BeforeEach
    public void setUp() {
        String questionText = "Match the capitals with their respective countries.";
        String[] questions = {"France", "Germany", "Italy"};
        String[] answers = {"Paris", "Berlin", "Rome"};
        matching = new Matching(questionText, questions, answers);
    }

    @Test
    public void testGetQuestion() {
        String expectedQuestion = "France,Germany,Italy:Berlin,Paris,Rome";
        String actualQuestion = matching.getQuestion();

        assertEquals(expectedQuestion.substring(0, expectedQuestion.indexOf(':')),
                actualQuestion.substring(0, actualQuestion.indexOf(':')));
        assertTrue(actualQuestion.contains("Berlin"));
        assertTrue(actualQuestion.contains("Paris"));
        assertTrue(actualQuestion.contains("Rome"));
    }

    @Test
    public void testEvaluateAllCorrectAnswers() {
        String answer = "France:Paris,Germany:Berlin,Italy:Rome";
        int expectedScore = 3;
        int actualScore = matching.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateSomeCorrectAnswers() {
        String answer = "France:Berlin,Germany:Berlin,Italy:Rome";
        int expectedScore = 2;
        int actualScore = matching.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateAllWrongAnswers() {
        String answer = "France:Berlin,Germany:Rome,Italy:Paris";
        int expectedScore = 0;
        int actualScore = matching.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateCaseInsensitive() {
        String answer = "france  :   paRis  ,  GERMANY: BERLIN,Italy:roMe";
        int expectedScore = 3;
        int actualScore = matching.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testSetAndGetTimer() {
        int timer = 30;
        matching.setTimer(timer);
        int actualTimer = matching.getTimer();
        assertEquals(timer, actualTimer);
    }

    @Test
    public void testGetNumFields() {
        int expectedNumFields = 3;
        int actualNumFields = matching.getNumFields();
        assertEquals(expectedNumFields, actualNumFields);
    }
}
