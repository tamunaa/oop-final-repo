package questionsDAOTests;

import static org.junit.jupiter.api.Assertions.*;

import dataBase.questionsDAOs.ResponseDAO;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ResponseDAOTest {

    private static Connection connection;
    private ResponseDAO responseDAO;

    @BeforeAll
    static void setupConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/test_database";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(jdbcUrl, user, password);
    }

    @AfterAll
    static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @BeforeEach
    void setup() {
        responseDAO = new ResponseDAO(connection);
    }

    @Test
    void testAddAndRetrieveResponse() {
        int questionId = 5;
        int historyId = 1;
        int grade = 90;
        boolean isGraded = true;
        String responseText = "My response to the question.";

        // Add the response to the database
        responseDAO.addResponse(questionId, historyId, grade, isGraded, responseText);

        // Retrieve the response from the database
       assertEquals("My response to the question.", responseDAO.getResponseByQuestionAndHistory(questionId, historyId));
    }

}
