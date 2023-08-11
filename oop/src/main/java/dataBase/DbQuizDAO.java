package dataBase;

import dataBase.questionsDAOs.QuestionsDAO;
import objects.Quiz;
import objects.Review;
import objects.questions.Question;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbQuizDAO implements QuizDAO{
    private final BasicDataSource dataSource;

    public DbQuizDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int addQuiz(Quiz quiz){
        int quiz_id = -1;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO QUIZZES(Author, Quiz_name, Descr, Timer, Date_created, Is_random, Display_type, Corrects_immediately, Is_practice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, quiz.getAuthor());
            statement.setString(2, quiz.getQuizName());
            statement.setString(3, quiz.getDescription());
            statement.setInt(4, quiz.getTimer());
            statement.setTimestamp(5, quiz.getDateCreated());
            statement.setBoolean(6, quiz.isRandom());
            statement.setBoolean(7, quiz.isOnOnePage());
            statement.setBoolean(8, quiz.correctImmediately());
            statement.setBoolean(9, quiz.isPractice());
            int rowsChanged = statement.executeUpdate();
            if(rowsChanged == 0){
                statement.close();
                connection.close();
                throw new SQLException("Inserting Quiz into Quizzes failed");
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                quiz_id = rs.getInt(1);
            } else {
                throw new SQLException("Inserting Quiz failed, no ID obtained.");
            }
            quiz.setID(quiz_id);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return quiz_id;
    }

    public boolean removeQuiz(int id){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM QUIZZES WHERE ID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Quiz> getQuizzesByAuthor(int author){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from QUIZZES where Author = ?");
            statement.setInt(1, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Quiz quiz = new Quiz(resultSet.getInt("Author"), resultSet.getString("Quiz_name"),
                        resultSet.getString("Descr"), resultSet.getInt("Timer"),
                        resultSet.getTimestamp("Date_created"), resultSet.getBoolean("Is_random"),
                        resultSet.getBoolean("Display_type"), resultSet.getBoolean("Corrects_immediately"),
                        resultSet.getBoolean("Is_practice"));
                quizzes.add(quiz);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzes;
    }

    public List<Quiz> getQuizByQuizName(String name){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from QUIZZES where Quiz_name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Quiz quiz = new Quiz(resultSet.getInt("Author"), resultSet.getString("Quiz_name"),
                        resultSet.getString("Descr"), resultSet.getInt("Timer"),
                        resultSet.getTimestamp("Date_created"), resultSet.getBoolean("Is_random"),
                        resultSet.getBoolean("Display_type"), resultSet.getBoolean("Corrects_immediately"),
                        resultSet.getBoolean("Is_practice"));
                quizzes.add(quiz);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzes;
    }

    public Quiz getQuizByID(int id){
        Quiz quiz = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from QUIZZES where ID = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                quiz = new Quiz(resultSet.getInt("Author"), resultSet.getString("Quiz_name"),
                        resultSet.getString("Descr"), resultSet.getInt("Timer"),
                        resultSet.getTimestamp("Date_created"), resultSet.getBoolean("Is_random"),
                        resultSet.getBoolean("Display_type"), resultSet.getBoolean("Corrects_immediately"),
                        resultSet.getBoolean("Is_practice"));
                quiz.setID(resultSet.getInt("ID"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quiz;
    }

    public List<Quiz> getPopularQuizzes(int top){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select Quiz_ID from HISTORY group by Quiz_ID order by count(*) desc limit ?");
            statement.setInt(1, top);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Quiz quiz = getQuizByID(resultSet.getInt("Quiz_ID"));
                quizzes.add(quiz);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzes;
    }

    public List<Question> getQuestions(Quiz quiz){
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select question_id from QUESTIONS where quiz_id = ?");
            statement.setInt(1, quiz.getID());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                QuestionsDAO quesDAO = new QuestionsDAO(dataSource);
                Question question = quesDAO.getQuestionByQuestionId(resultSet.getInt("question_id"));
                questions.add(question);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }


    public List<Quiz> recentlyCreatedQuizzes(int num){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from QUIZZES order by Date_created desc limit ?");
            statement.setInt(1, num);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Quiz quiz = new Quiz(resultSet.getInt("Author"), resultSet.getString("Quiz_name"),
                        resultSet.getString("Descr"), resultSet.getInt("Timer"),
                        resultSet.getTimestamp("Date_created"), resultSet.getBoolean("Is_random"),
                        resultSet.getBoolean("Display_type"), resultSet.getBoolean("Corrects_immediately"),
                        resultSet.getBoolean("Is_practice"));
                quiz.setID(resultSet.getInt("ID"));
                quizzes.add(quiz);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzes;
    }

    public List<String> getTags(Quiz quiz){
        ArrayList<String> tags = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select Hashtag from TAG where Quiz_ID = ?");
            statement.setInt(1, quiz.getID());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                tags.add(resultSet.getString("Hashtag"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tags;
    }

    public List<Review> getReviews(Quiz quiz){
        ArrayList<Review> reviews = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from REVIEW where Quiz_ID = ?");
            statement.setInt(1, quiz.getID());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Review review = new Review(resultSet.getInt("User_ID"), resultSet.getInt("Quiz_ID"),
                        resultSet.getString("Content"), resultSet.getTimestamp("Time_reviewed"));
                reviews.add(review);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    public Double getRating(Quiz quiz){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement( "select avg(Rating) from RATING where Quiz_ID = ?");
            statement.setInt(1, quiz.getID());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getDouble(1);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (double) -1;
    }

    public List<Quiz> getTopRatedQuizzes(int top){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select Quiz_ID from RATING group by Quiz_ID  order by avg(Rating) desc limit ?");
            statement.setInt(1, top);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Quiz quiz = getQuizByID(resultSet.getInt(1));
                quizzes.add(quiz);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzes;
    }
}
