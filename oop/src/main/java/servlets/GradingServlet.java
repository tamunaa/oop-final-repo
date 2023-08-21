package servlets;


import dataBase.UserDAO;
import dataBase.questionsDAOs.GradeDAO;
import objects.questions.GradedQuestion;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GradingServlet", value = "/grading")
public class GradingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("grading.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("grade".equals(action)) {
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            int score = Integer.parseInt(request.getParameter("score"));

            // Update the score for the specific question's response
            GradeDAO gradeDAO = (GradeDAO) getServletContext().getAttribute("gradeDAO");
            boolean result = gradeDAO.gradeSingleResponse(questionId, score);

            // Redirect back to the grading page with a success message (or error message if needed)
            if (result) {
                response.sendRedirect("grading?graded=true");
            } else {
                response.sendRedirect("grading?error=true");
            }
        } else if ("finishGrading".equals(action)) {
            // Update the overall score for the quiz
            int historyId = Integer.parseInt(request.getParameter("historyId"));

            GradeDAO gradeDAO = (GradeDAO) getServletContext().getAttribute("gradeDAO");
            boolean updateResult = gradeDAO.updateScore(historyId);

            // Redirect to a page indicating the grading is finished
            if (updateResult) {
                response.sendRedirect("gradingFinished.jsp");
            } else {
                response.sendRedirect("grading?error=true");
            }
        }
    }

}
