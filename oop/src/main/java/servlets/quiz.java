package servlets;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.JsonArray;
import dataBase.DbQuizDAO;
import dataBase.QuizDAO;
import dataBase.questionsDAOs.QuestionsDAO;
import objects.Quiz;
import objects.questions.Question;
import objects.questions.QuestionResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/quiz")
public class quiz extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        QuizDAO quizDAO = new DbQuizDAO(dataSource);

/*        UserDao userDao = new UserDao(dataSource); */
/*        HistoryDao historyDao = new HistoryDao(dataSource); */


        Quiz quiz = quizDAO.getQuizByID(quizId);
/*        String author = UserDao.getUserByUserId(quiz.getID()).getUsername(); */
        String author = "luka khukhua";

/*        List<History> historyList = HistoryDao.getHistoryByQuizId(quiz.getID()); */
        List<String> historyUsernames = new ArrayList<>();
/*        for (History history : historyList) {
            String username = UsersDao.getUserById(history.getUserId()).getUsername;
            historyUsernames.add(username);
        }
*/

        HttpSession session = request.getSession();
        session.setAttribute("quiz", quiz);
/*        request.setAttribute("historyList", historyList); */
        request.setAttribute("historyUsernames", historyUsernames);
        request.setAttribute("author", author);



        request.getRequestDispatcher("quiz.jsp").forward(request, response);


    }
}
