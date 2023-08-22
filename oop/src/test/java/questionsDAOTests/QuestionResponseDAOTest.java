package questionsDAOTests;

import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.FillInTheBlank;
import objects.questions.PictureResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import objects.questions.Question;
import objects.questions.QuestionResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuestionResponseDAOTest {
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
    public void testAddAndGetQuestionResponseType() throws SQLException {
        String questionText = "What is the capital of France?";
        String responseAnswer = "Paris";
        Question question = new QuestionResponse(questionText, responseAnswer);
        question.setTimer(17);

        int questionId = questionsDAO.addQuestion(question, 1);

        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);

        assertNotNull(retrievedQuestion);
        assertEquals(questionText, retrievedQuestion.getQuestion());
        assertEquals(responseAnswer, retrievedQuestion.getCorrectAnswers()[0]);
        assertEquals("QuestionResponse", retrievedQuestion.getQuestionType());
        assertEquals(17, retrievedQuestion.getTimer());
        assertEquals(1, retrievedQuestion.getNumFields());
        assertEquals(questionId, retrievedQuestion.getQuestionId());
    }

    @Test
    public void testRemoveQuestionResponseQuestion() throws SQLException {
        String questionText = "What is the capital of Germany?";
        String responseAnswer = "Berlin";
        Question question = new QuestionResponse(questionText, responseAnswer);
        int questionId = questionsDAO.addQuestion(question, 2);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(null, retrievedQuestion);
    }



    @Test
    public void testAddAndGetFillInTheBlankType() throws SQLException {
        String questionText = "The capital of Georgia is _.";
        String correctAnswer = "Tbilisi";
        Question question = new FillInTheBlank(questionText, correctAnswer);

        question.setTimer(17);

        int questionId = questionsDAO.addQuestion(question, 1);

        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);

        assertNotNull(retrievedQuestion);
        assertEquals(questionText, retrievedQuestion.getQuestion());
        assertEquals(correctAnswer, retrievedQuestion.getCorrectAnswers()[0]);
        assertEquals("FillInTheBlank", retrievedQuestion.getQuestionType());
        assertEquals(17, retrievedQuestion.getTimer());
        assertEquals(1, retrievedQuestion.getNumFields());
        assertEquals(questionId, retrievedQuestion.getQuestionId());
    }

    @Test
    public void testRemoveFillInTheBlankQuestion() throws SQLException {
        String questionText = "The capital of Georgia is _.";
        String correctAnswer = "Tbilisi";
        Question question = new FillInTheBlank(questionText, correctAnswer);

        int questionId = questionsDAO.addQuestion(question, 2);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(null, retrievedQuestion);
    }



    @Test
    public void testAddAndGetPictureResponseType() throws SQLException {
        String questionText = "What's in the picture?";
        String URL = "https://rameurl.com/images/questions/random-picture-of-a-bullet.png";
        String correctAnswer = "A bullet";
        Question question = new PictureResponse(questionText, URL, correctAnswer);
        question.setTimer(17);

        int questionId = questionsDAO.addQuestion(question, 1);

        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertNotNull(retrievedQuestion);
        assertEquals(questionText, retrievedQuestion.getQuestion());
        assertEquals(URL, retrievedQuestion.getOptions()[0]);
        assertEquals(correctAnswer, retrievedQuestion.getCorrectAnswers()[0]);
        assertEquals("PictureResponse", retrievedQuestion.getQuestionType());
        assertEquals(17, retrievedQuestion.getTimer());
        assertEquals(1, retrievedQuestion.getNumFields());
        assertEquals(questionId, retrievedQuestion.getQuestionId());
    }

    @Test
    public void testRemovePictureResponseQuestion() throws SQLException {
        String questionText = "What's in the picture?";
        String URL = "https://rameurl.com/images/questions/random-picture-of-a-bullet.png";
        String correctAnswer = "A bullet";
        Question question = new PictureResponse(questionText, URL, correctAnswer);
        int questionId = questionsDAO.addQuestion(question, 2);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(null, retrievedQuestion);
    }

}