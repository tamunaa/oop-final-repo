package servlets;

import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import objects.Quiz;
import objects.questions.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;


@WebServlet("/loadQuizQuestions")
public class loadQuizQuestions extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuizDAO quizDAO = (DbQuizDAO) request.getServletContext().getAttribute("quizDAO");
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        request.getSession().setAttribute("currQuizId",quizId);
        List<Question> questionsList = quizDAO.getQuestions(quizId);
        Quiz quiz = quizDAO.getQuizByID(quizId);
        Question[] questions = new Question[questionsList.size()];
        questions = questionsList.toArray(questions);

        java.sql.Timestamp st_time = new java.sql.Timestamp(System.currentTimeMillis());
        request.getSession().setAttribute("startTime", st_time);
        request.getSession().setAttribute("timeLeft", quiz.getTimer() * 10);

        request.getSession().setAttribute("IsPracticed", request.getParameter("IsPracticed"));

        if(quiz.isOnOnePage()){
            request.getSession().setAttribute("questions", questions);
            request.getRequestDispatcher("singlePageQuiz.jsp?quizId=" + quizId).forward(request, response);
        } else {
            if (quiz.isRandom()) {
                List<Question> quest = Arrays.asList(questions);
                Collections.shuffle(quest);
                questions = (Question[]) quest.toArray();
            }
            request.getSession().setAttribute("questions", questions);
           request.getSession().setAttribute("feedback", Boolean.FALSE);
            request.getRequestDispatcher("multiplePageQuiz.jsp?quizId=" + quizId).forward(request, response);
        }
    }
}
