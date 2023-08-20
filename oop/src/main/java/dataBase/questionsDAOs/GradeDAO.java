package dataBase.questionsDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GradeDAO {
    private Connection connection;

    public void GradeDAO(Connection connection) {
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
}
