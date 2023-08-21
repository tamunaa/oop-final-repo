package questionsDAOTests;

import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.AutoGeneratedQuestion;
import objects.questions.MultipleChoice;
import objects.questions.MultipleChoiceWithMultipleAnswer;
import objects.questions.Question;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleChoiceDAOTest {
    private BasicDataSource dataSource;
    private QuestionsDAO questionsDAO;


    @BeforeEach
    public void setup() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        questionsDAO = new QuestionsDAO(dataSource);
    }

    @Test
    public void testAddAndGetMultipleChoiceType() {
        String questionText = "What is the capital of France?";
        String[] choices = {"Paris", "Berlin", "Rome"};
        String correctAnswer = "Paris";
        Question question = new MultipleChoice(questionText, choices, correctAnswer);

        question.setTimer(17);

        int questionId = questionsDAO.addQuestion(question, 1);

        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);

        assertNotNull(retrievedQuestion);
        assertEquals(questionText, retrievedQuestion.getQuestion());
        assertArrayEquals(choices, retrievedQuestion.getOptions());
        assertEquals(correctAnswer, retrievedQuestion.getCorrectAnswers()[0]);
        assertEquals("MultipleChoice", retrievedQuestion.getQuestionType());
        assertEquals(17, retrievedQuestion.getTimer());
        assertEquals(3, retrievedQuestion.getNumFields());
        assertEquals(questionId, retrievedQuestion.getQuestionId());
    }


    @Test
    public void testRemoveOrderedMultipleChoice() {
        String questionText = "What is the capital of France?";
        String[] choices = {"Paris", "Berlin", "Rome"};
        String correctAnswer = "Paris";
        Question question = new MultipleChoice(questionText, choices, correctAnswer);

        int questionId = questionsDAO.addQuestion(question, 2);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(null, retrievedQuestion);
    }



    @Test
    public void testAddAndGetMultipleChoiceMultipleAnswerType() {
        String questionText = "Select all the countries in Europe.";
        String[] choices = {"France", "Germany", "Italy", "Nigeria"};
        String[] correctAnswers = {"France", "Germany", "Italy"};
        HashSet<String> correctAnswersSet = new HashSet<>();
        for (String answer : correctAnswersSet) {
            correctAnswersSet.add(answer);
        }
        Question question = new MultipleChoiceWithMultipleAnswer(questionText, choices, correctAnswers);

        question.setTimer(17);

        int questionId = questionsDAO.addQuestion(question, 1);

        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);

        assertNotNull(retrievedQuestion);
        assertEquals(questionText, retrievedQuestion.getQuestion());
        assertArrayEquals(choices, retrievedQuestion.getOptions());
        for (String answer : retrievedQuestion.getCorrectAnswers()) {
            assertFalse(correctAnswersSet.contains(answer));
        }
        assertEquals("MultipleChoiceWithMultipleAnswer", retrievedQuestion.getQuestionType());
        assertEquals(17, retrievedQuestion.getTimer());
        assertEquals(4, retrievedQuestion.getNumFields());
        assertEquals(questionId, retrievedQuestion.getQuestionId());
    }

    @Test
    public void testRemoveOrderedMultipleChoiceMultipleAnswer() {
        String questionText = "Select all the countries in Europe.";
        String[] choices = {"France", "Germany", "Italy", "Nigeria"};
        String[] correctAnswers = {"France", "Germany", "Italy"};
        Question question = new MultipleChoiceWithMultipleAnswer(questionText, choices, correctAnswers);

        int questionId = questionsDAO.addQuestion(question, 2);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(null, retrievedQuestion);
    }

    @Test
    public void testAddAutoGeneratedType(){
        Question autoGeneratedQuestion = new AutoGeneratedQuestion();
        autoGeneratedQuestion.setTimer(17);

        int questionId = questionsDAO.addQuestion(autoGeneratedQuestion, 3);
        Question retrievedQuestion1 = questionsDAO.getQuestionByQuestionId(questionId);
        Question retrievedQuestion2 = questionsDAO.getQuestionByQuestionId(questionId);
        assertNotNull(retrievedQuestion1);
        assertNotNull(retrievedQuestion2);

        String questionText = retrievedQuestion1.getQuestion();
        String[] choices = retrievedQuestion1.getOptions();
        String correctAnswer = retrievedQuestion1.getCorrectAnswers()[0];
        int timer = retrievedQuestion1.getTimer();
        int numFields = retrievedQuestion1.getNumFields();
        questionId = retrievedQuestion1.getQuestionId();

        assertEquals(questionText, retrievedQuestion2.getQuestion());
        assertArrayEquals(choices, retrievedQuestion2.getOptions());
        assertEquals(correctAnswer, retrievedQuestion2.getCorrectAnswers()[0]);
        assertEquals("MultipleChoice", retrievedQuestion2.getQuestionType());
        assertEquals(timer, retrievedQuestion2.getTimer());
        assertEquals(numFields, retrievedQuestion2.getNumFields());
        assertEquals(questionId, retrievedQuestion2.getQuestionId());

    }

    @Test
    public void testRemoveAutoGeneratedType() {
        Question autoGeneratedQuestion = new AutoGeneratedQuestion();

        int questionId = questionsDAO.addQuestion(autoGeneratedQuestion, 3);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        Question sameRetrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(retrievedQuestion, sameRetrievedQuestion);
    }
}