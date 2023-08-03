package questionsTests;

import objects.questions.MultipleChoiceWithMultipleAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipleChoiceWithMultipleAnswerTest {

    private MultipleChoiceWithMultipleAnswer multipleChoiceWithMultipleAnswer;

    @BeforeEach
    public void setUp() {
        String questionText = "Select all the countries in Europe.";
        String[] choices = {"France", "Germany", "Italy", "Nigeria"};
        String[] correctAnswers = {"France", "Germany", "Italy"};
        multipleChoiceWithMultipleAnswer = new MultipleChoiceWithMultipleAnswer(questionText, choices, correctAnswers);
    }

    @Test
    public void testGetQuestion() {
        String expectedQuestion = "Select all the countries in Europe.:France,Germany,Italy,Nigeria";
        String actualQuestion = multipleChoiceWithMultipleAnswer.getQuestion();
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    public void testEvaluateAllCorrectAnswers() {
        String answer = "France,Germany,Italy";
        int expectedScore = 3;
        int actualScore = multipleChoiceWithMultipleAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateSomeCorrectAnswers() {
        String answer = "Germany,Italy,Nigeria";
        int expectedScore = 1;
        int actualScore = multipleChoiceWithMultipleAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateOnlyOneCorrectAnswer() {
        String answer = "Italy";
        int expectedScore = 1;
        int actualScore = multipleChoiceWithMultipleAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateAllWrongAnswers() {
        String answer = "UK,USA,Canada";
        int expectedScore = 0;
        int actualScore = multipleChoiceWithMultipleAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateCaseInsensitive() {
        String answer = "   fRance,  geRMany ,  iTAly ";
        int expectedScore = 3;
        int actualScore = multipleChoiceWithMultipleAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testSetAndGetTimer() {
        int timer = 30;
        multipleChoiceWithMultipleAnswer.setTimer(timer);
        int actualTimer = multipleChoiceWithMultipleAnswer.getTimer();
        assertEquals(timer, actualTimer);
    }

    @Test
    public void testGetNumFields() {
        int expectedNumFields = 4;
        int actualNumFields = multipleChoiceWithMultipleAnswer.getNumFields();
        assertEquals(expectedNumFields, actualNumFields);
    }
}
