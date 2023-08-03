package questionsTests;

import objects.questions.MultiAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiAnswerTest {

    private MultiAnswer orderedMultiAnswer;
    private MultiAnswer unorderedMultiAnswer;

    @BeforeEach
    public void setUp() {
        String questionText = "Whats the capitals of France, Germany and Italy?";
        String[] answers = {"Paris", "Berlin", "Rome"};
        int numFields = answers.length;

        orderedMultiAnswer = new MultiAnswer(questionText, answers, numFields, true);
        unorderedMultiAnswer = new MultiAnswer(questionText, answers, numFields, false);
    }

    @Test
    public void testGetQuestion() {
        String expectedQuestion = "Whats the capitals of France, Germany and Italy?";
        String actualQuestionOrdered = orderedMultiAnswer.getQuestion();
        String actualQuestionUnordered = unorderedMultiAnswer.getQuestion();

        assertEquals(expectedQuestion, actualQuestionOrdered);
        assertEquals(expectedQuestion, actualQuestionUnordered);
    }

    @Test
    public void testEvaluateOrderedMultiAnswerCorrect() {
        String answer = "Paris,Berlin,Rome";
        int expectedScore = 3;
        int actualScore = orderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateOrderedMultiAnswerIncorrect() {
        String answer = "Berlin,Paris,Rome";
        int expectedScore = 1;
        int actualScore = orderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateUnorderedMultiAnswerCorrect() {
        String answer = "Paris,Rome,Berlin";
        int expectedScore = 3;
        int actualScore = unorderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateUnorderedMultiAnswerSomeCorrect() {
        String answer = "Berlin,Paris,Tbilisi";
        int expectedScore = 2;
        int actualScore = unorderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateUnorderedMultiAnswerAllWrongAnswers() {
        String answer = "Tbilisi,Batumi,Qutaisi";
        int expectedScore = 0;
        int actualScore = unorderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateOrderedMultiAnswerAllWrongAnswers() {
        String answer = "Tbilisi,Batumi,Qutaisi";
        int expectedScore = 0;
        int actualScore = orderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }
    @Test
    public void testEvaluateUnorderedMultiAnswerSameCorrectAnswers() {
        String answer = "Berlin,Berlin,Berlin";
        int expectedScore = 1;
        int actualScore = unorderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateOrderedMultiAnswerIncorrectOrder() {
        String answer = "Rome,Paris,Berlin";
        int expectedScore = 0;
        int actualScore = orderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateUnorderedMultiAnswerIncorrectOrder() {
        String answer = "Rome,Paris,Berlin";
        int expectedScore = 3;
        int actualScore = unorderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateOrderedMultiAnswerSameCorrectAnswers() {
        String answer = "Berlin,Berlin,Berlin";
        int expectedScore = 1;
        int actualScore = orderedMultiAnswer.evaluate(answer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testSetAndGetTimer() {
        int timer = 20;
        orderedMultiAnswer.setTimer(timer);
        unorderedMultiAnswer.setTimer(timer);

        int actualTimerOrdered = orderedMultiAnswer.getTimer();
        int actualTimerUnordered = unorderedMultiAnswer.getTimer();

        assertEquals(timer, actualTimerOrdered);
        assertEquals(timer, actualTimerUnordered);
    }

    @Test
    public void testGetNumFields() {
        int expectedNumFields = 3;
        int actualNumFieldsOrdered = orderedMultiAnswer.getNumFields();
        int actualNumFieldsUnordered = unorderedMultiAnswer.getNumFields();

        assertEquals(expectedNumFields, actualNumFieldsOrdered);
        assertEquals(expectedNumFields, actualNumFieldsUnordered);
    }
}
