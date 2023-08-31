package servlets;

import dataBase.QuizDAO;
import objects.*;
import objects.questions.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MultiPageServlet", value = "/MultiPageServlet")
public class MultiPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Question[] questions = (Question[]) request.getSession().getAttribute("questions");
        int index = Integer.parseInt(request.getAttribute("index").toString());
        int timeLeft = (int) Integer.parseInt(request.getParameter("timeLeft"));
        request.getSession().setAttribute("timeLeft" , timeLeft);
       QuizDAO quizDAO = (QuizDAO) request.getServletContext().getAttribute("quizDAO");
        int quizId = Integer.parseInt((String) request.getSession().getAttribute("currQuizId"));
        Quiz currentQuiz = quizDAO.getQuizByID(quizId);

        if (currentQuiz.correctImmediately() && !(Boolean) request.getSession().getAttribute("feedback")) {
            request.getRequestDispatcher("/ImmediateFeedback.jsp").forward(request, response);
        } else {
            index++;
            if (index == questions.length || timeLeft <= 2) {
                request.getRequestDispatcher("/CheckServlet").forward(request, response);
            } else {
                Question firstQuestion = questions[index];
                if (currentQuiz.correctImmediately()) request.getSession().setAttribute("feedback", false);
                request.getRequestDispatcher("questions/" + firstQuestion.getQuestionType() + ".jsp").forward(request, response);
            }
        }
    }
}