package questionsDAOTests;

import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class MultiAnswerDAOTest {
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
    public void testAddAndGetOrderedMultiAnswerType() {
        String questionText = "Whats the capitals of France, Germany and Italy?";
        String[] answers = {"Paris", "Berlin", "Rome"};
        int numFields = answers.length;

        Question question = new MultiAnswer(questionText, answers, numFields, true);
        question.setTimer(17);

        int questionId = questionsDAO.addQuestion(question, 1);

        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);

        assertNotNull(retrievedQuestion);
        assertEquals(questionText, retrievedQuestion.getQuestion());
        assertArrayEquals(answers, retrievedQuestion.getCorrectAnswers());
        assertEquals("MultiAnswer", retrievedQuestion.getQuestionType());
        assertEquals(17, retrievedQuestion.getTimer());
        assertEquals(3, retrievedQuestion.getNumFields());
        assertEquals(questionId, retrievedQuestion.getQuestionId());
    }

    @Test
    public void testRemoveOrderedMultiQuestion() {
        String questionText = "Whats the capitals of France, Germany and Italy?";
        String[] answers = {"Paris", "Berlin", "Rome"};
        int numFields = answers.length;

        Question question = new MultiAnswer(questionText, answers, numFields, true);
        int questionId = questionsDAO.addQuestion(question, 2);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(null, retrievedQuestion);
    }

    @Test
    public void testAddAndGetUnorderedMultiAnswerType() {
        String questionText = "Whats the capitals of France, Germany and Italy?";
        String[] answers = {"Paris", "Berlin", "Rome"};
        HashSet<String> answersSet = new HashSet<>();
        int numFields = answers.length;

        Question question = new MultiAnswer(questionText, answers, numFields, false);
        question.setTimer(17);

        int questionId = questionsDAO.addQuestion(question, 1);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);

        assertNotNull(retrievedQuestion);
        assertEquals(questionText, retrievedQuestion.getQuestion());
        for (String answer : retrievedQuestion.getCorrectAnswers()) {
            assertFalse(answersSet.contains(answer));
        }
        assertEquals("MultiAnswer", retrievedQuestion.getQuestionType());
        assertEquals(17, retrievedQuestion.getTimer());
        assertEquals(3, retrievedQuestion.getNumFields());
        assertEquals(questionId, retrievedQuestion.getQuestionId());
    }


    @Test
    public void testRemoveUnorderedMultiQuestion() {
        String questionText = "Whats the capitals of France, Germany and Italy?";
        String[] answers = {"Paris", "Berlin", "Rome"};
        int numFields = answers.length;

        Question question = new MultiAnswer(questionText, answers, numFields, false);
        int questionId = questionsDAO.addQuestion(question, 2);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(null, retrievedQuestion);
    }



}