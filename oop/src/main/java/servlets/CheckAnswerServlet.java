package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/check", "/questions/check"})
public class CheckAnswerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = "tamunaaa"; // Replace with your desired username
//        HttpSession session = request.getSession();
//        session.setAttribute("username", username);

//        response.sendRedirect("questions/FillInTheBlank.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String answer = (String) request.getParameter("userAnswer");
//        System.out.println(answer);
//        response.sendRedirect("questions/QuestionResponse.jsp");
    }
}
