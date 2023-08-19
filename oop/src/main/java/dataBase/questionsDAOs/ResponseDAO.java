package dataBase.questionsDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseDAO {
    private Connection connection;

    public ResponseDAO(Connection connection) {
        this.connection = connection;
    }

    public void addResponse(int questionId, int historyId, int grade, boolean isGraded, String response) {
        String query = "INSERT INTO RESPONSES (Question_ID, History_ID, grade, Is_graded, Response) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            statement.setInt(2, historyId);
            statement.setInt(3, grade);
            statement.setBoolean(4, isGraded);
            statement.setString(5, response);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //for testing
    public String getResponseByQuestionAndHistory(int questionId, int historyId) {
        String query = "SELECT * FROM RESPONSES WHERE Question_ID = ? AND History_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            statement.setInt(2, historyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
//                    int responseId = resultSet.getInt("ID");
//                    int grade = resultSet.getInt("grade");
//                    boolean isGraded = resultSet.getBoolean("Is_graded");
                    String response = resultSet.getString("Response");

                    return response;
                } else {
                    return null; // No response found with the given IDs
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null in case of an error
        }
    }
}
