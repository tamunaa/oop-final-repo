package servlets;


import com.mysql.cj.conf.ConnectionUrlParser;
import dataBase.UserDAO;
import dataBase.questionsDAOs.GradeDAO;
import dataBase.questionsDAOs.ResponseDAO;
import objects.QuestionResponsePair;
import objects.Response;
import objects.questions.GradedQuestion;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataBase.*;
import objects.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "name", value = "/grading")
public class GradingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = (UserDAO) getServletContext().getAttribute("userDAO");

        System.out.println("username "+ username + "password " + password);

        if(!userDAO.isValidUser(username, password)) {
            request.setAttribute("incorrect", true);
            request.setAttribute("mess", "Username Or Password Is Not Correct");
            request.getRequestDispatcher("invalidUser.jsp").forward(request, response);
            return;
        }
        User user = userDAO.getUserByUsername(username);

        String profileUrl = "profile.jsp?self=true";
        request.getSession().setAttribute("currUser", user);
        response.sendRedirect(profileUrl);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("index.jsp");
    }
}


//
//
//@WebServlet(name = "GradingServlet", value = "/grading")
//public class GradingServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Retrieve the list of questions from the database
////        ResponseDAO responseDAO = (ResponseDAO) getServletContext().getAttribute("responseDAO");
////        List<QuestionResponsePair> questionResponsePairs;
////        try {
////            questionResponsePairs = responseDAO.getUngradedResponsesByAuthorID(Integer.parseInt(request.getParameter("1")));
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////        System.out.println(questionResponsePairs);
////        request.setAttribute("responses", questionResponsePairs);
////
//
//        // Set the list of questions as an attribute in the request
//        //request.setAttribute("questions", questionResponses);
//
////        request.getSession().setAttribute("questions", questionResponses);
//        // Forward to the grading.jsp page
//        request.getRequestDispatcher("grading.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if ("grade".equals(action)) {
//            int questionId = Integer.parseInt(request.getParameter("questionId"));
//            int score = Integer.parseInt(request.getParameter("score"));
//
//            // Update the score for the specific question's response
//            GradeDAO gradeDAO = (GradeDAO) getServletContext().getAttribute("gradeDAO");
//            boolean result = gradeDAO.gradeSingleResponse(questionId, score);
//
//            // Redirect back to the grading page with a success message (or error message if needed)
//            if (result) {
//                response.sendRedirect("grading?graded=true");
//            } else {
//                response.sendRedirect("grading?error=true");
//            }
//        } else if ("finishGrading".equals(action)) {
//            // Update the overall score for the quiz
//            int historyId = Integer.parseInt(request.getParameter("historyId"));
//
//            GradeDAO gradeDAO = (GradeDAO) getServletContext().getAttribute("gradeDAO");
//            boolean updateResult = gradeDAO.updateScore(historyId);
//
//            // Redirect to a page indicating the grading is finished
//            if (updateResult) {
//                response.sendRedirect("gradingFinished.jsp");
//            } else {
//                response.sendRedirect("grading?error=true");
//            }
//        }
//    }
//
//}
//
//
