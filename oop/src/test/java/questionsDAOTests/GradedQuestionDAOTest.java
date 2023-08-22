package questionsDAOTests;

import static org.junit.jupiter.api.Assertions.*;

import dataBase.questionsDAOs.GradedQuestionDAO;
import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.GradedQuestion;
import objects.questions.Question;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GradedQuestionDAOTest {

    private static BasicDataSource connection;
    private static GradedQuestionDAO gradedQuestionDAO;

    @BeforeAll
    static void setupConnection() throws SQLException {
        connection = new BasicDataSource();
        connection.setUrl("jdbc:mysql://localhost:3306/test_database");
        connection.setUsername("root");
        connection.setPassword("");
        gradedQuestionDAO = new GradedQuestionDAO(connection);
    }

    @AfterAll
    static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @BeforeEach
    void setup() throws SQLException {
        gradedQuestionDAO = new GradedQuestionDAO(connection);
    }

    @Test
    void testAddAndGetQuestion() {
        GradedQuestion gradedQuestion = new GradedQuestion("What is 2 + 2?");
        gradedQuestion.setTimer(30);

        int quizId = 1;
        int questionId = gradedQuestionDAO.addQuestion(gradedQuestion, quizId);

        assertNotEquals(-1, questionId);

        Question retrievedQuestion = gradedQuestionDAO.getQuestionByQuestionId(questionId);
        assertNotNull(retrievedQuestion);
        assertTrue(retrievedQuestion instanceof GradedQuestion);
        assertEquals("What is 2 + 2?", retrievedQuestion.getQuestion());
        assertEquals(30, retrievedQuestion.getTimer());
    }
    @Test
    public void GetGradedQuestionText() {
       Question gradedQuestion = gradedQuestionDAO.getQuestionByQuestionId(1);
       assertEquals("What is 2 + 2?", gradedQuestion.getQuestion());

       gradedQuestion = gradedQuestionDAO.getQuestionByQuestionId(2);
       assertEquals("Solve for x: 3x + 5 = 20", gradedQuestion.getQuestion());

       gradedQuestion = gradedQuestionDAO.getQuestionByQuestionId(3);
       assertEquals("What is the chemical symbol for water?", gradedQuestion.getQuestion());
    }

    @Test
    public void GetGradedQuestionTimer() {
        Question gradedQuestion = gradedQuestionDAO.getQuestionByQuestionId(1);
        assertEquals(30, gradedQuestion.getTimer());

        gradedQuestion = gradedQuestionDAO.getQuestionByQuestionId(2);
        assertEquals(45, gradedQuestion.getTimer());

        gradedQuestion = gradedQuestionDAO.getQuestionByQuestionId(3);
        assertEquals(40, gradedQuestion.getTimer());
    }

    @Test
    public void testGetNonexistentQuestion() {
        int nonExistentQuestionId = -1;
        int nonExistentQuestionId2 = 7;
        assertEquals(null, gradedQuestionDAO.getQuestionByQuestionId(nonExistentQuestionId));
        assertEquals(null, gradedQuestionDAO.getQuestionByQuestionId(nonExistentQuestionId2));
    }
}
