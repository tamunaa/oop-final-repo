package servlets;

import dataBase.*;
import objects.*;
import objects.questions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SinglePageCheckServlet", value = "/SinglePageCheckServlet")
public class SinglePageCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Question[] questions = (Question[]) request.getSession().getAttribute("questions");
        for (int i=0;i<questions.length;i++) {
            String[] answers = request.getParameterValues("answers");
        }
        System.out.println(request.getParameter("timeLeft"));
        request.getRequestDispatcher("CheckServlet").forward(request,response);
    }
}
