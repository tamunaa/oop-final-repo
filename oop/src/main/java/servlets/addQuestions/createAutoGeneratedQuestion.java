package servlets.addQuestions;

import objects.questions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createQuestion/createAutoGeneratedQuestion.jsp")
public class createAutoGeneratedQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Question question = new AutoGeneratedQuestion();

        request.setAttribute("question", question);
        request.getRequestDispatcher("AutoGeneratedQuestion.jsp?quizId=" + quizId).forward(request, response);
    }
}