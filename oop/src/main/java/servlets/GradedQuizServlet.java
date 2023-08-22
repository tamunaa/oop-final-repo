package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gradedQuiz")
public class GradedQuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("submit")) {
            // Assuming you have a data storage mechanism to process the answers.
            // For example, you might want to retrieve the submitted scores and save them.

            // Redirect the user after processing.
            response.sendRedirect("result.jsp"); // Replace "result.jsp" with the appropriate page
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
}
