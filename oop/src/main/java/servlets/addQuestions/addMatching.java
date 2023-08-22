package servlets.addQuestions;

import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.Matching;
import objects.questions.MultipleChoice;
import objects.questions.Question;
import objects.questions.QuestionResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/addQuestions/addMatching")
public class addMatching extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");

        QuizDAO quizDAO = new DbQuizDAO(dataSource);

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String questionText = request.getParameter("questionText");


        String[] left = request.getParameterValues("left");
        String[] right = request.getParameterValues("right");
        String timerStr = request.getParameter("timer");
        int timer = timerStr.equals("") ? 0 : Integer.parseInt(timerStr);


        Question question = new Matching(questionText, left, right);
        question.setTimer(timer);
        QuestionsDAO questionsDAO = new QuestionsDAO(dataSource);
        try {
            questionsDAO.addQuestion(question, quizId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/editQuiz?quizId=" + quizId);
    }
}
