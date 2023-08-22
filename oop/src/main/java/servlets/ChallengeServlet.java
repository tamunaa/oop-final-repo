package servlets;

import dataBase.*;
import objects.*;
import objects.messages.ChallengeMessage;
import objects.messages.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "ChallengeServlet", value = "/ChallengeServlet")
public class ChallengeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDAO");
        QuizDAO quizDAO = (QuizDAO) request.getServletContext().getAttribute("quizDAO");
        MessageDAO messageDAO = (MessageDAO) request.getServletContext().getAttribute("messageDAO");

        User user = (User) request.getSession().getAttribute("currUser");
        User friend = userDAO.getUserByUserId(Integer.parseInt(request.getParameter("recieverID")));
        if (friend == null || friend.getId() == user.getId()) return;

        int quizId = Integer.parseInt((String) request.getSession().getAttribute("challengedQuizID"));

        Message challenge = new ChallengeMessage(user.getId(), friend.getId(),"quiz.jsp?quizId=" + quizId , new Timestamp(System.currentTimeMillis()));
        messageDAO.addMessage(challenge);

        response.sendRedirect("profile.jsp");
    }
}