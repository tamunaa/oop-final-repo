package dataBase;

import objects.History;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dataBase.HistoryDAO.ERROR;
import static dataBase.HistoryDAO.SUCCESS;

public class HistoryDAOSQL implements HistoryDAO{
    private BasicDataSource dataSource;

    public HistoryDAOSQL(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int insertHistory(History history) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO HISTORY (Quiz_ID, User_ID, score, Time_relapsed, Date_taken) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setInt(1, history.getQuizId());
            preparedStatement.setInt(2, history.getUserId());
            preparedStatement.setInt(3, history.getScore());
            preparedStatement.setInt(4, history.getTimeElapsed());
            preparedStatement.setTimestamp(5, history.getDateTaken());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    history.setId(generatedId);
                    return SUCCESS;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ERROR;
    }

    @Override
    public History getHistoryById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM HISTORY WHERE ID = ?"
            );

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int quizId = resultSet.getInt("Quiz_ID");
                int userId = resultSet.getInt("User_ID");
                int score = resultSet.getInt("score");
                int timeElapsed = resultSet.getInt("Time_relapsed");
                Timestamp dateTaken = resultSet.getTimestamp("Date_taken");

                History history = new History(quizId, userId, score, timeElapsed, dateTaken);
                history.setId(id);
                return history;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<History> getHistoryByUserId(int userId) {
        List<History> historyList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM HISTORY WHERE User_ID = ?"
            );

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int quizId = resultSet.getInt("Quiz_ID");
                int score = resultSet.getInt("score");
                int timeElapsed = resultSet.getInt("Time_relapsed");
                Timestamp dateTaken = resultSet.getTimestamp("Date_taken");

                History history = new History(quizId, userId, score, timeElapsed, dateTaken);
                history.setId(id);
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }

    @Override
    public List<History> getHistoryByQuizId(int quizId) {
        List<History> historyList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM HISTORY WHERE Quiz_ID = ?"
            );

            preparedStatement.setInt(1, quizId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int userId = resultSet.getInt("User_ID");
                int score = resultSet.getInt("score");
                int timeElapsed = resultSet.getInt("Time_relapsed");
                Timestamp dateTaken = resultSet.getTimestamp("Date_taken");

                History history = new History(quizId, userId, score, timeElapsed, dateTaken);
                history.setId(id);
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }
}
