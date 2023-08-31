package servlets;

import dataBase.*;
import dataBase.questionsDAOs.QuestionsDAO;
import objects.*;
import objects.questions.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import java.util.stream.Collectors;

@WebServlet(name = "CheckServlet", value = "/CheckServlet")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        request.getSession().setAttribute("endTime", endTime);
        int score = 0;
        Question[] questions = (Question[]) request.getSession().getAttribute("questions");
        String[] answers = request.getParameterValues("answers");
        int maxScore = 0;
        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            int curScore = 0;
            if( i < answers.length) curScore = question.evaluate(answers[i]);
            int cur_max_score = question.getCorrectAnswers().length;
            score += curScore;
            maxScore += cur_max_score;
        }

        int quizId = Integer.parseInt((String) request.getSession().getAttribute("currQuizId"));
        String isPracticed = (String) request.getSession().getAttribute("IsPracticed");

        if (isPracticed == null || !isPracticed.equals("YES")) {
            int userId = ((User)request.getSession().getAttribute("currUser")).getId();
            double time = ((((Timestamp)request.getSession().getAttribute("endTime")).getTime()-
                    ((Timestamp) request.getSession().getAttribute("startTime")).getTime()) / 1000);

            History newHistory = new History(quizId,userId,score,time,(Timestamp)request.getSession().getAttribute("endTime"));
            HistoryDAO historyDAO = (HistoryDAO) request.getServletContext().getAttribute("historyDAO");
            historyDAO.insertHistory(newHistory);
        }
        request.getSession().setAttribute("FinalScore", score);
        request.getSession().setAttribute("MaxScore", maxScore);
        request.getRequestDispatcher("/quizResult.jsp").forward(request, response);
    }
}
