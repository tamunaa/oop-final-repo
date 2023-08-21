package servlets;

import objects.questions.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/main"})
public class Main extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("posthiaaa");

        Question question1 = new QuestionResponse("capital city of england?", "london");
        Question question2 = new FillInTheBlank("One of President Lincoln’s most famous\n" +
                "speeches was the __________ Address.", "answer");
        Question question3 = new MultipleChoice("this is question", new String[]{"A", "B", "C"}, "A");
        Question question4 = new MultiAnswer("question", new String[]{"A", "B"}, false);
        Question question5 = new MultipleChoiceWithMultipleAnswer("question5", new String[]{"A", "B"}, new String[]{"A", "B"});
        Question question6 = new Matching("rame",new String[]{"A", "B"}, new String[]{"A", "B"});

        Question[] ls = new Question[]{question1, question2, question3, question4, question5, question6};
        request.getSession().setAttribute("questions", ls);
        RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getshiaa");
        RequestDispatcher dispatcher = request.getRequestDispatcher("questions/Matching.jsp");
        dispatcher.forward(request, response);
    }

}
