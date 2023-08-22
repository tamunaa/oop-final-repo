package servlets.addQuestions;

import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.GradedQuestion;
import objects.questions.Question;
import objects.questions.QuestionResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addQuestions/addGraded")
public class addGraded extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionsDAO questionsDAO = (QuestionsDAO) request.getServletContext().getAttribute("QuestionsDAO");

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String questionText = request.getParameter("questionText");
        String timerStr = request.getParameter("timer");
        int timer = timerStr.equals("") ? 0 : Integer.parseInt(timerStr);


        Question question = new GradedQuestion(questionText);
        question.setTimer(timer);
        questionsDAO.addQuestion(question, quizId);

        response.sendRedirect("/editQuiz?quizId=" + quizId);
    }
}