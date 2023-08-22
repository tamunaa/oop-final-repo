package servlets;

import dataBase.AnnouncementDAOSQL;
import dataBase.UserDAO;
import objects.Announcement;
import objects.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!((User) request.getSession().getAttribute("currUser")).isAdmin()) {
            response.sendRedirect("profile.jsp");
            return;
        }
        UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDAO");
        if (request.getParameter("deleteUser") != null) {
            String deleteUserName = (String) request.getParameter("deleteUser");
            int userId = userDAO.getIDByUsername(deleteUserName);
            userDAO.removeUser(userId);
        }
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!((User) request.getSession().getAttribute("currUser")).isAdmin()) {
            response.sendRedirect("profile.jsp?self=true");
            return;
        }
        AnnouncementDAOSQL announcementDAO = (AnnouncementDAOSQL) request.getServletContext().getAttribute("announcementDAO");

        if (request.getParameter("announcementTitle") != null) {
            String announcementTitle = request.getParameter("announcementTitle");
            String announcementText = request.getParameter("announcementText");
            announcementDAO.insertAnnouncement(new Announcement((((User) request.getSession().getAttribute("currUser")).getId()), new Timestamp(new java.util.Date().getTime()), announcementTitle, announcementText));
        }
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}

