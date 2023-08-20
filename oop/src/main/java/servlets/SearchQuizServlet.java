package servlets;

import dataBase.QuizDAO;
import objects.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "SearchQuizServlet", value = "/search_quiz")
public class SearchQuizServlet extends HttpServlet {

    private QuizDAO quizDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        quizDAO = (QuizDAO) request.getServletContext().getAttribute("quizDao");
        String quizName = request.getParameter("quizName");

        List<Quiz> searchResults = quizName == null ? Collections.emptyList() : quizDAO.getQuizByQuizName(quizName);

        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("WEB-INF/search-quiz.jsp").forward(request, response);
    }
}
