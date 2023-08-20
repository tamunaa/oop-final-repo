package servlets;

import dataBase.UserDAO;
import objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "SearchUserServlet", value = "/search_user")
public class SearchUserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDao");
        String username = request.getParameter("username");

        User searchResult = username == null ? null : userDAO.getUserByUsername(username);

        request.setAttribute("searchResult", searchResult);
        request.getRequestDispatcher("WEB-INF/search-user.jsp").forward(request, response);
    }
}
