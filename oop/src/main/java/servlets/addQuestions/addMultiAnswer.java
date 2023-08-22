package servlets.addQuestions;
import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.MultiAnswer;
import objects.questions.MultipleChoice;
import objects.questions.Question;
import objects.questions.QuestionResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;

@WebServlet("/addQuestions/addMultiAnswer")
public class addMultiAnswer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionsDAO questionsDAO = (QuestionsDAO) request.getServletContext().getAttribute("QuestionsDAO");


        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String questionText = request.getParameter("questionText");


        String[] answers = request.getParameterValues("answers");
        String timerStr = request.getParameter("timer");
        int timer = timerStr.equals("") ? 0 : Integer.parseInt(timerStr);

        String orderingValue = request.getParameter("ordering");
        boolean isOrdered = "ordered".equals(orderingValue);
        Question question = new MultiAnswer(questionText, answers, isOrdered);
        question.setTimer(timer);

        questionsDAO.addQuestion(question, quizId);

        response.sendRedirect("/editQuiz?quizId=" + quizId);
    }
}