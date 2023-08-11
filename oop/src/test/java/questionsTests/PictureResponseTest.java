package questionsTests;

import objects.questions.PictureResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PictureResponseTest {

    private PictureResponse pictureResponse;

    @BeforeEach
    public void setUp() {
        String questionText = "https://rameurl.com/images/questions/random-picture-of-a-bullet.png";
        String correctAnswer = "A bullet";
        pictureResponse = new PictureResponse(questionText, correctAnswer);
    }

    @Test
    public void testGetQuestion() {
        String expectedQuestionText = "https://rameurl.com/images/questions/random-picture-of-a-bullet.png";
        String actualQuestionText = pictureResponse.getQuestion();
        assertEquals(expectedQuestionText, actualQuestionText);
    }

    @Test
    public void testEvaluateCorrectAnswer() {
        String correctAnswer = "A bullet";
        int expectedScore = 1;
        int actualScore = pictureResponse.evaluate(correctAnswer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateIncorrectAnswer1() {
        String incorrectAnswer = "an umbrella";
        int expectedScore = 0;
        int actualScore = pictureResponse.evaluate(incorrectAnswer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateIncorrectAnswer2() {
        String incorrectAnswer = "An bullet";
        int expectedScore = 0;
        int actualScore = pictureResponse.evaluate(incorrectAnswer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testEvaluateCaseInsensitive() {
        String correctAnswer = "  a BulLet ";
        int expectedScore = 1;
        int actualScore = pictureResponse.evaluate(correctAnswer);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testSetAndGetTimer() {
        int timer = 10;
        pictureResponse.setTimer(timer);
        int actualTimer = pictureResponse.getTimer();
        assertEquals(timer, actualTimer);
    }

    @Test
    public void testGetNumFields() {
        int expectedNumFields = 1;
        int actualNumFields = pictureResponse.getNumFields();
        assertEquals(expectedNumFields, actualNumFields);
    }

    @Test
    public void testGetQuestionType() {
        assertEquals("PictureResponse", pictureResponse.getQuestionType());
    }

    @Test
    public void testIsOrdered() {
        assertEquals(false, pictureResponse.isOrdered());
    }

    @Test
    public void testGetOptions() {
        assertArrayEquals(null, pictureResponse.getOptions());
    }

    @Test
    public void testGetAnswers() {
        assertArrayEquals(new String[] {"A bullet"}, pictureResponse.getCorrectAnswers());
    }


    @Test
    public void testSetAndGetQuestionId() {
        assertEquals(-1, pictureResponse.getQuestionId());
        int questionId = 28;
        pictureResponse.setQuestionId(questionId);
        assertEquals(questionId, pictureResponse.getQuestionId());
    }
}
