import dataBase.FriendsDAO;
import dataBase.FriendshipService;
import dataBase.MessageDAO;
import dataBase.UserDAO;
import dataBase.questionsDAOs.GradeDAO;
import dataBase.questionsDAOs.ResponseDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.commons.dbcp2.BasicDataSource;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {
    private Connection conn;

    public ContextListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/oopquizzweb");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setMaxTotal(-1);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = dataSource.getConnection();
            this.conn.createStatement().execute("USE oopquizzweb;");
        } catch (ClassNotFoundException | SQLException var12) {
            throw new RuntimeException(var12);
        }

        ServletContext context = sce.getServletContext();
        UserDAO userDAO = new UserDAO(dataSource);
        FriendsDAO friendsDAO = new FriendsDAO(dataSource);
        MessageDAO messageDAO = new MessageDAO(dataSource);

        GradeDAO gradeDAO;
        try {
            gradeDAO = new GradeDAO(dataSource);
        } catch (SQLException var11) {
            throw new RuntimeException(var11);
        }

        ResponseDAO responseDAO;
        try {
            responseDAO = new ResponseDAO(dataSource);
        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }

        context.setAttribute("userDAO", userDAO);
        context.setAttribute("friendsDAO", friendsDAO);
        context.setAttribute("messageDAO", messageDAO);
        context.setAttribute("responseDAO", responseDAO);
        context.setAttribute("gradeDAO", gradeDAO);
        FriendshipService service = new FriendshipService();
        context.setAttribute("friendshipService", service);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("userDAO");
        sce.getServletContext().removeAttribute("friendsDAO");
        sce.getServletContext().removeAttribute("messageDAO");
        sce.getServletContext().removeAttribute("friendshipService");
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("currUser", (Object)null);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }
}
