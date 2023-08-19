import objects.questions.GradedQuestion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradedQuestionTest {
    private GradedQuestion gradedQuestion;

    @Before
    public void setUp() {
        String questionText = "What is the capital of France?";
        gradedQuestion = new GradedQuestion(questionText);
    }

    @Test
    public void testGetQuestion() {
        String expectedQuestionText = "What is the capital of France?";
        assertEquals(expectedQuestionText, gradedQuestion.getQuestion());
    }

    @Test
    public void testSetAndGetTimer() {
        gradedQuestion.setTimer(30);
        assertEquals(30, gradedQuestion.getTimer());
    }

    @Test
    public void testSetAndGetAnswer() {
        gradedQuestion.setAnswer("Paris");
        assertEquals("Paris", gradedQuestion.getAnswer());
    }

    @Test
    public void testGetQuestionType() {
        String expectedQuestionType = "Graded";
        assertEquals(expectedQuestionType, gradedQuestion.getQuestionType());
    }

    @Test
    public void testGetSetQuestionId() {
        gradedQuestion.setQuestionId(123);
        assertEquals(123, gradedQuestion.getQuestionId());
    }

    @Test
    public void testDefaultQuestionId() {
        assertEquals(-1, gradedQuestion.getQuestionId());
    }
}
