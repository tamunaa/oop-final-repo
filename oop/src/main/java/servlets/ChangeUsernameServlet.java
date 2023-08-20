package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import dataBase.*;
import objects.User;


@WebServlet(name = "ChangePasswordServlet", value = "/ChangePasswordServlet")
public class ChangeUsernameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = (String) request.getSession().getAttribute("email");
        String username = request.getParameter("username");
        if(userEmail == null || username == null){
            try{
                request.getRequestDispatcher("invalidUser.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDAO");
        User currUser = userDAO.getUserByEmail(userEmail);
        boolean res = userDAO.changeUsername(currUser, username);
        if(res){
            response.sendRedirect("success.jsp");
        }else{
            request.setAttribute("incorrect", true);
            request.setAttribute("mess", "Failed to update username. Please try again.");
            request.getRequestDispatcher("invalidUser.jsp").forward(request,response);
        }
    }

}