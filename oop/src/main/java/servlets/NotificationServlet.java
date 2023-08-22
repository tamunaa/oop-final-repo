package servlets;

import objects.messages.*;
import objects.User;
import dataBase.UserDAO;
import dataBase.MessageDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NotificationServlet", value = "/NotificationServlet")
public class NotificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currUser");
        if (user == null) return;
        String recipient = request.getParameter("recipient");
        String type = request.getParameter("type");
        String content = request.getParameter("content");

        if (recipient == null || type == null) return;
        if (type.equals("CHALLENGE") && content == null) return;
        if (type.equals("CHALLENGE") && content.isEmpty()) return;

        UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDAO");
        User recipientUser = userDAO.getUserByUsername(recipient);
        if (recipientUser == null || recipientUser.getId() == user.getId()) {
            return;
        }
        MessageDAO messageDAO = (MessageDAO) request.getServletContext().getAttribute("messageDAO");
        Message newNotification = null;
        if(type.equals("CHALLENGE")) {
            newNotification = new ChallengeMessage(user.getId(),recipientUser.getId(),content);
        }else if(type.equals("REQUEST")) {
            newNotification = new RequestMessage(user.getId(),recipientUser.getId());
        }

        messageDAO.addMessage(newNotification);

        response.sendRedirect("homepage.jsp");
    }
}