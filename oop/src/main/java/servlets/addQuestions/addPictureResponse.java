package servlets.addQuestions;

import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import dataBase.questionsDAOs.QuestionsDAO;
import objects.questions.PictureResponse;
import objects.questions.Question;
import objects.questions.QuestionResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addQuestions/addPictureResponse")
public class addPictureResponse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");

        QuizDAO quizDAO = new DbQuizDAO(dataSource);

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String questionText = request.getParameter("questionText");
        String pictureURL = request.getParameter("pictureURL");
        String answer = request.getParameter("answer");
        String timerStr = request.getParameter("timer");
        int timer = timerStr.equals("") ? 0 : Integer.parseInt(timerStr);

        Question question = new PictureResponse(questionText, pictureURL, answer);
        question.setTimer(timer);
        QuestionsDAO questionsDAO = new QuestionsDAO(dataSource);
        questionsDAO.addQuestion(question, quizId);

        response.sendRedirect("/editQuiz?quizId=" + quizId);
    }
}
