package dataBase.questionsDAOs;

import objects.Response;

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

    //get responses which are not graded yet
    public Response getResponseByHistory(int historyId) {
        String query = "SELECT * FROM RESPONSES WHERE History_ID = ? AND Is_graded = ? LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, historyId);
            statement.setBoolean(2, false);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    int questionId = resultSet.getInt("Question_ID");
                    int grade = resultSet.getInt("grade");
                    boolean isGraded = resultSet.getBoolean("Is_graded");
                    String responseText = resultSet.getString("Response");

                    return new Response(id, questionId, historyId, grade, isGraded, responseText);
                } else {
                    return null; // No ungraded response found with the given IDs
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addScoreAndMarkAsGraded(int responseId, int grade) {
        String query = "UPDATE RESPONSES SET grade = ?, Is_graded = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, grade);
            statement.setBoolean(2, true);
            statement.setInt(3, responseId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was affected
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an error
        }
    }



    //for tests
    public String getResponseByQuestionAndHistory(int questionId, int historyId) {
        String query = "SELECT * FROM RESPONSES WHERE Question_ID = ? AND History_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            statement.setInt(2, historyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
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