package questionsDAOTests;

import static org.junit.jupiter.api.Assertions.*;

import dataBase.HistoryDAOSQL;
import dataBase.questionsDAOs.GradeDAO;
import dataBase.questionsDAOs.ResponseDAO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ResponseDAOTest {

    private static BasicDataSource connection;
    private ResponseDAO responseDAO;
    private static GradeDAO gradeDAO;

    @BeforeAll
    static void setupConnection() throws SQLException {
        connection = new BasicDataSource();
        connection.setUrl("jdbc:mysql://localhost:3306/test_db");
        connection.setUsername("root");
        connection.setPassword("root:root");
        gradeDAO = new GradeDAO(connection);
    }

    @AfterAll
    static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @BeforeEach
    void setup() throws SQLException {
        responseDAO = new ResponseDAO(connection);
        gradeDAO = new GradeDAO(connection);
    }

    @Test
    void testAddAndRetrieveResponse() throws SQLException {
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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3}) // history IDs here
    void testGetResponseByHistoryId(int historyId) {
        System.out.println(responseDAO.getResponseByHistory(historyId));
    }

    @Test
    void testGetResponseByHistoryId() {
        int historyId = 1;
        String expectedResponse = "Paris";

        assertEquals(expectedResponse, responseDAO.getResponseByHistory(historyId).getResponseText());
        assertEquals(1, responseDAO.getResponseByHistory(historyId).getId());
        assertEquals(1, responseDAO.getResponseByHistory(historyId).getQuestionId());
        assertEquals(0, responseDAO.getResponseByHistory(historyId).getGrade());
        assertFalse(responseDAO.getResponseByHistory(historyId).isGraded());
    }

    @Test
    void testScoreAndGetResponse(){
        int historyId = 1;

        assertEquals("Paris", responseDAO.getResponseByHistory(historyId).getResponseText());
        assertEquals(1, responseDAO.getResponseByHistory(historyId).getId());
        assertEquals(1, responseDAO.getResponseByHistory(historyId).getQuestionId());
        assertEquals(0, responseDAO.getResponseByHistory(historyId).getGrade());
        assertFalse(responseDAO.getResponseByHistory(historyId).isGraded());

        responseDAO.addScoreAndMarkAsGraded(responseDAO.getResponseByHistory(historyId).getId(), 1);
        assertNotEquals("Paris", responseDAO.getResponseByHistory(historyId).getResponseText());

        assertEquals("Mars", responseDAO.getResponseByHistory(historyId).getResponseText());
        assertEquals(2, responseDAO.getResponseByHistory(historyId).getId());
        assertEquals(2, responseDAO.getResponseByHistory(historyId).getQuestionId());
        assertEquals(0, responseDAO.getResponseByHistory(historyId).getGrade());
        assertFalse(responseDAO.getResponseByHistory(historyId).isGraded());
    }

    @Test
    void TestNullResponse(){
        responseDAO.addScoreAndMarkAsGraded(2, 0);
        assertNull(responseDAO.getResponseByHistory(1));
    }


    //GRADE DAO TEST

//    +----+-------------+------------+-------+-----------+----------+
//            | ID | Question_ID | History_ID | grade | Is_graded | Response |
//            +----+-------------+------------+-------+-----------+----------+
//            |  1 |           1 |          1 |     1 |         1 | Paris    |
//            |  2 |           2 |          1 |     0 |         1 | Mars     |
//            |  3 |           3 |          1 |   100 |         1 | Au       |
//            +----+-------------+------------+-------+-----------+----------+
    @Test
    void TestGradeDAO(){
        responseDAO.getResponseByHistory(1);
        assertEquals(101, gradeDAO.getScoreByHistoryId(1));
    }

    @Test
    void TestUngradedGradeDAO(){
        assertEquals(0, gradeDAO.getScoreByHistoryId(2));
    }

    @Test
    void TestGetQuestionsByHistoryId(){
        assertEquals(23, responseDAO.getQuestionResponsePairsByHistory(1).size());
    }
}
