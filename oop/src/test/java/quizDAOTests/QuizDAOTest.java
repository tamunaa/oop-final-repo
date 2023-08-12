package quizDAOTests;

import dataBase.DbQuizDAO;
import dataBase.questionsDAOs.QuestionsDAO;
import junit.framework.TestCase;
import objects.Quiz;
import objects.Review;
import objects.questions.Matching;
import objects.questions.MultiAnswer;
import objects.questions.Question;
import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class QuizDAOTest extends TestCase {
    private Connection connection;
    private Statement statement;
    private DbQuizDAO quizDAO;
    private BasicDataSource dataSource;
    public void beforeEach() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_quiz");
        dataSource.setUsername("root");
        dataSource.setPassword("123456789");

        quizDAO = new DbQuizDAO(dataSource);

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute("USE test_quiz");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearQuizzes() throws SQLException {
        statement.execute("DELETE FROM quizzes");
    }

    public void testAddQuizzes(){
        beforeEach();
        Quiz quiz = new Quiz(1, "qvizi3", "satesto quiz" ,20);
        quiz.setDateCreated(Timestamp.valueOf("2023-08-11 19:51:50"));
        assertEquals(quizDAO.addQuiz(quiz), quiz.getID());
    }

    public void testRemoveQuiz(){
        beforeEach();
        assertEquals(true, quizDAO.removeQuiz(8));
    }

    public void testGetQuizzesByAuthor(){
        beforeEach();
        List<Quiz> quizzes = quizDAO.getQuizzesByAuthor(1);
        assertEquals(quizzes.size(), 6);
        Quiz q1 = quizzes.get(1);
        assertEquals(q1.getAuthor(), 1);
        assertEquals(q1.getTimer(), 20);
        assertEquals(q1.getQuizName(), "qvizi");
        assertEquals(q1.getDescription(), "satesto quiz");

        quizzes = quizDAO.getQuizzesByAuthor(2);
        assertEquals(quizzes.size(), 6);
        Quiz q2 = quizzes.get(1);
        assertEquals(q2.getAuthor(), 2);
        assertEquals(q2.getTimer(), 30);
        assertEquals(q2.getQuizName(), "qvizi2");
        assertEquals(q2.getDescription(), "martivi quiz");
    }

    public void testGetQuizByQuizName(){
        beforeEach();
//        Quiz quiz = new Quiz(1, "qvizi2", "satesto quiz" ,15);
//        quizDAO.addQuiz(quiz);
//        quiz = new Quiz(2, "qvizi", "sashualo quiz" ,40);
//        quizDAO.addQuiz(quiz);

        List<Quiz> quizzes = quizDAO.getQuizByQuizName("qvizi");
        assertEquals(8, quizzes.size());
        Quiz q1 = quizzes.get(4);
        assertEquals(2, q1.getAuthor());
        assertEquals(40, q1.getTimer());
        assertEquals("qvizi",q1.getQuizName());
        assertEquals("sashualo quiz", q1.getDescription());

        q1 = quizzes.get(2);
        assertEquals(1, q1.getAuthor());
        assertEquals(20, q1.getTimer());
        assertEquals("qvizi",q1.getQuizName());
        assertEquals("satesto quiz", q1.getDescription());
    }

    public void testGetQuizByID(){
        beforeEach();
        Quiz quiz;
//        quiz = new Quiz(2, "qvizi", "sashualo quiz" ,35);
//        quiz.setRandom(true);
//        quiz.setPractice(true);
//        quizDAO.addQuiz(quiz);

        quiz = quizDAO.getQuizByID(21);
        assertEquals(2, quiz.getAuthor());
        assertEquals(35, quiz.getTimer());
        assertEquals("qvizi",quiz.getQuizName());
        assertEquals("sashualo quiz", quiz.getDescription());
        assertEquals(true, quiz.isPractice());
        assertEquals(true, quiz.isRandom());
        assertEquals(false, quiz.isOnOnePage());
        assertEquals(false, quiz.correctImmediately());
    }

    public void testGetPopularQuizzes(){
        beforeEach();
        ArrayList<Quiz> quizzes = (ArrayList<Quiz>) quizDAO.getPopularQuizzes(2);
        assertEquals(20, quizzes.get(0).getID());
        assertEquals(18, quizzes.get(1).getID());
    }

    private void addQuestions() throws SQLException {
        statement.execute("delete from QUESTIONS");
        String questionText = "Match the capitals with their respective countries.";
        String[] questions = {"France", "Germany", "Italy"};
        String[] answers = {"Paris", "Berlin", "Rome"};
        Question question = new Matching(questionText, questions, answers);

        question.setTimer(17);

        QuestionsDAO questionsDAO = new QuestionsDAO(dataSource);
        int questionId = questionsDAO.addQuestion(question, 20);

        questionText = "Whats the capitals of France, Germany and Italy?";
        answers = new String[]{"Paris", "Berlin", "Rome"};
        int numFields = answers.length;

        question = new MultiAnswer(questionText, answers, numFields, true);
        question.setTimer(17);
        questionId = questionsDAO.addQuestion(question, 20);

        questionText = "Whats the capitals of France, Germany and Italy?";
        answers = new String[]{"Paris", "Berlin", "Rome"};
        HashSet<String> answersSet = new HashSet<>();
        numFields = answers.length;

        question = new MultiAnswer(questionText, answers, numFields, false);
        question.setTimer(15);

        questionId = questionsDAO.addQuestion(question, 22);
    }

    public void testGetQuestions() throws SQLException {
        beforeEach();
//        addQuestions();
        Quiz quiz = new Quiz(2,"qvizi","sashualo quiz",40);
        quiz.setDateCreated(Timestamp.valueOf("2023-08-11 19:55:41"));
        quiz.setID(20);
        List<Question> questions = quizDAO.getQuestions(quiz);
        assertEquals(2, questions.size());
        assertEquals("Match the capitals with their respective countries.", questions.get(0).getQuestion());
        assertEquals("Whats the capitals of France, Germany and Italy?", questions.get(1).getQuestion());
        assertEquals(17, questions.get(0).getTimer());
        assertEquals(17, questions.get(1).getTimer());
    }

    public void testRecentlyCreatedQuizzes(){
        beforeEach();
        List<Quiz> quizzes = quizDAO.recentlyCreatedQuizzes(5);
        assertEquals(24, quizzes.get(0).getID());
        assertEquals(23, quizzes.get(1).getID());
        assertEquals(22, quizzes.get(2).getID());
        assertEquals(21, quizzes.get(3).getID());
        assertEquals(19, quizzes.get(4).getID());
    }

    private void addTags() throws SQLException {
        statement.execute("INSERT INTO TAG(Quiz_ID, Hashtag) VALUES(18, 'Simple');");
        statement.execute("INSERT INTO TAG(Quiz_ID, Hashtag) VALUES(20, 'Medium');");
        statement.execute("INSERT INTO TAG(Quiz_ID, Hashtag) VALUES(18, 'Martivi');");
        statement.execute("INSERT INTO TAG(Quiz_ID, Hashtag) VALUES(20, 'Medium');");
    }

    public void testGetTags() throws SQLException {
        beforeEach();
//        addTags();
        Quiz quiz = new Quiz(2,"qvizi","sashualo quiz",40);
        quiz.setDateCreated(Timestamp.valueOf("2023-08-11 19:55:41"));
        quiz.setID(20);
        ArrayList<String> tags = (ArrayList<String>) quizDAO.getTags(quiz);
        assertEquals(1, tags.size());
        assertEquals("Medium", tags.get(0));
    }


    private void addReviews() throws SQLException {
        statement.execute("INSERT INTO REVIEW(User_ID,Quiz_ID, Content) VALUES(1, 18, 'Good');");
        statement.execute("INSERT INTO REVIEW(User_ID,Quiz_ID, Content) VALUES(2, 19, 'Liked it!');");
        statement.execute("INSERT INTO REVIEW(User_ID,Quiz_ID, Content) VALUES(2, 18, 'Not easy!');");
    }
    public void testGetReviews() throws SQLException {
        beforeEach();
//        addReviews();
        Quiz quiz = new Quiz(2,"qvizi2","martivi quiz",30);
        quiz.setDateCreated(Timestamp.valueOf("2023-08-11 19:51:35"));
        quiz.setID(18);
        List<Review> reviews = quizDAO.getReviews(quiz);
        assertEquals(2, reviews.size());
        assertEquals(1, reviews.get(0).getUser_id());
        assertEquals(18, reviews.get(0).getQuiz_id());
        assertEquals("Good", reviews.get(0).getContent());
        assertEquals(2, reviews.get(1).getUser_id());
        assertEquals(18, reviews.get(1).getQuiz_id());
        assertEquals("Not easy!", reviews.get(1).getContent());
    }

    private void addRating() throws SQLException {
        statement.execute("INSERT INTO RATING(User_ID, Quiz_ID, Rating) VALUES(1, 20, 9);");
        statement.execute("INSERT INTO RATING(User_ID, Quiz_ID, Rating) VALUES(2, 18, 9.5);");
        statement.execute("INSERT INTO RATING(User_ID, Quiz_ID, Rating) VALUES(2, 20, 9.3);");

    }

    public void testGetRating() throws SQLException {
        beforeEach();
//        addRating();
        Quiz quiz = new Quiz(2,"qvizi","sashualo quiz",40);
        quiz.setDateCreated(Timestamp.valueOf("2023-08-11 19:55:41"));
        quiz.setID(20);
        assertEquals(9.15, quizDAO.getRating(quiz));
    }

    public void testGetTopRatedQuizzes(){
        beforeEach();
        List<Quiz> quizes = quizDAO.getTopRatedQuizzes(1);
        assertEquals(1, quizes.size());
        assertEquals(18, quizes.get(0).getID());

    }

}
