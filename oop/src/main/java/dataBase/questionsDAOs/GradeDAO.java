package dataBase.questionsDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeDAO {
    private Connection connection;

    public GradeDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean updateScore(int historyId, int score) {
        String query = "UPDATE HISTORY SET score = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, score);
            statement.setInt(2, historyId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getScoreByHistoryId(int historyId) {
        String query = "SELECT score FROM HISTORY WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, historyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("score");
                } else {
                    return -1; // No history found with the given ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Return -1 in case of an error
        }
    }
}
