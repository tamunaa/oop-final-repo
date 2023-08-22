package questionsDAOTests;

import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.Matching;
import objects.questions.Question;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class MatchingDAOTest {
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
    public void testAddAndGetMatchingType() throws SQLException {
        String questionText = "Match the capitals with their respective countries.";
        String[] questions = {"France", "Germany", "Italy"};
        String[] answers = {"Paris", "Berlin", "Rome"};
        Question question = new Matching(questionText, questions, answers);

        question.setTimer(17);

        int questionId = questionsDAO.addQuestion(question, 1);

        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);

        assertNotNull(retrievedQuestion);
        assertEquals(questionText, retrievedQuestion.getQuestion());
        assertArrayEquals(questions, retrievedQuestion.getOptions());
        assertArrayEquals(answers, retrievedQuestion.getCorrectAnswers());
        assertEquals("Matching", retrievedQuestion.getQuestionType());
        assertEquals(17, retrievedQuestion.getTimer());
        assertEquals(3, retrievedQuestion.getNumFields());
        assertEquals(questionId, retrievedQuestion.getQuestionId());
    }

    @Test
    public void testRemoveOrderedMultiQuestion() throws SQLException {
        String questionText = "Match the capitals with their respective countries.";
        String[] questions = {"France", "Germany", "Italy"};
        String[] answers = {"Paris", "Berlin", "Rome"};
        Question question = new Matching(questionText, questions, answers);

        int questionId = questionsDAO.addQuestion(question, 2);

        questionsDAO.removeQuestion(questionId);
        Question retrievedQuestion = questionsDAO.getQuestionByQuestionId(questionId);
        assertEquals(null, retrievedQuestion);
    }



}