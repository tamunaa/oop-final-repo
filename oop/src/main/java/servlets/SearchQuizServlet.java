package servlets;

import dataBase.QuizDAO;
import dataBase.UserDAO;
import objects.Quiz;
import objects.User;

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
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        quizDAO = (QuizDAO) request.getServletContext().getAttribute("quizDao");
        String passed = request.getParameter("quizName");

        List<Quiz> searchResults = passed == null ? Collections.emptyList() : quizDAO.getQuizByQuizName(passed);

        //search quizzes by author username
        userDAO = (UserDAO) request.getServletContext().getAttribute("quizDao");
        User author = userDAO.getUserByUsername(passed);
        if(author != null){
            int userID = author.getId();
            List<Quiz> searchResultsByAuthor = passed == null ? Collections.emptyList() : quizDAO.getQuizzesByAuthor(userID);
            searchResults.addAll(searchResultsByAuthor);
        }

        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("WEB-INF/search-quiz.jsp").forward(request, response);
    }
}
