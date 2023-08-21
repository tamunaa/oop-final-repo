package dataBase;

import objects.Achievement;
import objects.Challenge;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbAchievementDAO implements AchievementDAO{
    private BasicDataSource dataSource;

    public DbAchievementDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Achievement> getUserAchievements(int userid) {
        ArrayList<Achievement> achievements = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select Achievement_ID from USER_ACHIEVEMENT where User_ID = ?");
            statement.setInt(1, userid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Achievement achievement = getAchievementByID(resultSet.getInt("Achievement_ID"));
                achievements.add(achievement);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
        return achievements;
    }

    @Override
    public boolean addUserAchievement(int userid, int achievementID) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO USER_ACHIEVEMENT(User_ID, Achievement_ID) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userid);
            statement.setInt(2, achievementID);
            int rowsChanged = statement.executeUpdate();
            if(rowsChanged != 1){
                statement.close();
                connection.close();
                return false;
            }
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Achievement getAchievementByID(int id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from ACHIEVEMENTS where ID = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Achievement achievement = new Achievement(resultSet.getString("Achievement_type"),
                        resultSet.getString("Picture_url"),  resultSet.getString("Achievement_desc"));
                achievement.setAchievementID(id);
                return achievement;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int addAchievement(Achievement achievement) {
        int achievement_id = -1;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ACHIEVEMENTS(Achievement_type, Picture_url, Achievement_desc) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, achievement.getAchievementType());
            statement.setString(2, achievement.getPictureUrl());
            statement.setString(3, achievement.getAchievementDesc());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                achievement_id = rs.getInt(1);
            } else {
                throw new SQLException("Inserting Quiz failed, no ID obtained.");
            }
            achievement.setAchievementID(achievement_id);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return achievement_id;
    }

    @Override
    public boolean removeAchievement(int achievementID) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ACHIEVEMENTS WHERE ID = ?");
            statement.setInt(1, achievementID);
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
