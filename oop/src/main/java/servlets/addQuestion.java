package servlets;

import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addQuestion")
public class addQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");

        QuizDAO quizDAO = new DbQuizDAO(dataSource);

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String questionType = request.getParameter("questionType");

        boolean timerIsAllowed = quizDAO.getQuizByID(quizId).isOnOnePage();
        request.setAttribute("timerIsAllowed", !timerIsAllowed);
        request.getRequestDispatcher("createQuestion/create" + questionType + ".jsp?quizId=" + quizId).forward(request, response);
    }
}
