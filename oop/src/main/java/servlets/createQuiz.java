package servlets;

import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import objects.Quiz;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


@WebServlet("/createQuiz")
public class createQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");

        int author = 7;
//        int author = (Integer) request.getSession().getAttribute("userId");
        String quizName = request.getParameter("quizName");
        int timer = Integer.parseInt(request.getParameter("timer"));

        boolean practiceMode = request.getParameter("practice") != null;
        String questionDisplayMode = request.getParameter("questionDisplay") != null ? request.getParameter("questionDisplay") : "";
        boolean immediateCorrection = request.getParameter("correction") != null;
        String quizDescription = request.getParameter("quizDescription");
        boolean randomOrder = request.getParameter("randomOrder") != null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        QuizDAO quizDAO = new DbQuizDAO(dataSource);

        Quiz quiz = new Quiz(author, quizName, quizDescription, timer);
        quiz.setPractice(practiceMode);
        quiz.setCorrectImmediately(immediateCorrection);
        quiz.setOnOnePage(questionDisplayMode.equals("All Questions on a Single Page"));
        int quizId = quizDAO.addQuiz(quiz);
        response.sendRedirect("editQuiz?quizId=" + quizId);
    }
}
