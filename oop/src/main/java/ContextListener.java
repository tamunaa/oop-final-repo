import dataBase.questionsDAOs.ResponseDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;
import java.sql.SQLException;
import objects.*;
import dataBase.*;
@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener  {
    private Connection conn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/oopquizzweb");
        dataSource.setUsername("root");
        dataSource.setPassword("root:root");
        dataSource.setMaxTotal(-1);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = dataSource.getConnection();
            conn.createStatement().execute("USE oopquizzweb;");
        } catch (SQLException | ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }

        ServletContext context = sce.getServletContext();
        UserDAO userDAO = new UserDAO(dataSource);
        FriendsDAO friendsDAO = new FriendsDAO(dataSource);
        MessageDAO messageDAO = new MessageDAO(dataSource);
        ResponseDAO responseDAO = null;
        try {
            responseDAO = new ResponseDAO(dataSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        context.setAttribute("userDAO", userDAO);
        context.setAttribute("friendsDAO", friendsDAO);
        context.setAttribute("messageDAO", messageDAO);
        context.setAttribute("responseDAO", responseDAO);

        FriendshipService service = new FriendshipService();
        context.setAttribute("friendshipService", service);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("userDAO");
        sce.getServletContext().removeAttribute("friendsDAO");
        sce.getServletContext().removeAttribute("messageDAO");
        sce.getServletContext().removeAttribute("friendshipService");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("currUser", null);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}