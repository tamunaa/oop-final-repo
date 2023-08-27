package servlets;

import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import objects.Quiz;
import objects.questions.*;
import org.apache.commons.dbcp2.BasicDataSource;
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

@WebServlet("/quizSummary")
public class quizSummary extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Question[] questions = (Question[]) request.getSession().getAttribute("questions");
        String[] answers = request.getParameterValues("answers");

        System.out.println("quiId: " + quizId);
        for (int i = 0; i < answers.length; i++) {
            System.out.println("question is: " + questions[i].getQuestion());
            System.out.println("my answer is: " + answers[i]);
        }
    }

}