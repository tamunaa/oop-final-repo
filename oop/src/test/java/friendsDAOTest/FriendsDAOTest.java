package friendsDAOTest;

import dataBase.DbQuizDAO;
import dataBase.FriendsDAO;
import dataBase.UserDAO;
import objects.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class FriendsDAOTest {
    private Connection connection;
    private Statement statement;
    private UserDAO userDAO;
    private FriendsDAO friendsDAO;
    private BasicDataSource dataSource;
    private User tako = new User("tako","tako@gmail.com","justPass");
    private User shako = new User("shako","shako@gmail.com","justPass");
    private User mako = new User("mako","mako@gmail.com","justPass");

    @BeforeAll
    public static void emptyTables() throws SQLException {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/test_friend");
        ds.setUsername("root");
        ds.setPassword("root");
        Connection conn = ds.getConnection();
        PreparedStatement stm = conn.prepareStatement("USE test_friend ; ");
        stm.execute();
        stm = conn.prepareStatement("DELETE FROM USERS ;");
        stm.executeUpdate();
        stm = conn.prepareStatement("DELETE FROM FRIENDS ;");
        stm.executeUpdate();
        stm = conn.prepareStatement("DELETE FROM FRIEND_REQS;");
        stm.executeUpdate();
        stm.close();
        conn.close();

    }
    @BeforeEach
    public void setUp() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_friend");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        userDAO = new UserDAO(dataSource);
        friendsDAO = new FriendsDAO(dataSource);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute("USE test_friend");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @AfterEach
    public void tearDown() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void addRequestTest(){

        userDAO.addUser(tako);
        userDAO.addUser(shako);
        userDAO.addUser(mako);
        assertTrue(friendsDAO.addFriendshipRequest(tako,shako));
        assertFalse(friendsDAO.addFriendshipRequest(tako,shako));
        assertTrue(friendsDAO.addFriendshipRequest(tako,mako));

        List<User> reqs = friendsDAO.getAllFriendshipRequests(tako);
        assertEquals(2,reqs.size());
       for (int i = 0; i < reqs.size(); i++){
            User curr = reqs.get(i);
            assertTrue(curr.equals(shako) || curr.equals(mako));
            }
    }
    @Test
    @Order(2)
    void removeRequestTest(){

        assertFalse(friendsDAO.addFriendshipRequest(shako,shako));

      /*  assertTrue(friendsDAO.isInFriendshipRequests(tako,shako));
        assertFalse(friendsDAO.isInFriendshipRequests(shako,tako));
        assertFalse(friendsDAO.isInFriendshipRequests(shako,mako));
        assertTrue(friendsDAO.isInFriendshipRequests(mako,shako));

        assertTrue(friendsDAO.removeFriendshipRequest(tako,shako));
        assertFalse(friendsDAO.removeFriendshipRequest(tako,shako));
        assertFalse(friendsDAO.isInFriendshipRequests(tako,shako));

        List<User> reqs1 = friendsDAO.getAllFriendshipRequests(tako);
        List<User> reqs2 = friendsDAO.getAllFriendshipRequests(shako);
        assertEquals(1,reqs1.size());
        assertEquals(0,reqs2.size());
        assertTrue(mako.equals(reqs1.get(0))); */
    }
/*
    @Test
    @Order(3)
    void addFriendshipTest(){
        assertTrue(friendsDAO.addFriendshipRequest(mako,tako));
        assertTrue(friendsDAO.addFriendship(tako,mako));

        assertTrue(friendsDAO.isFriendship(tako,mako));
        assertTrue(friendsDAO.isFriendship(mako,tako));

        assertFalse(friendsDAO.addFriendship(tako,mako));
        assertTrue(friendsDAO.addFriendship(mako,tako));

        List<User> fr1 = friendsDAO.getAllFriends(tako);
        List<User> fr2 = friendsDAO.getAllFriends(mako);
        assertTrue(fr1.size() == 1 && fr2.size() == 1);
        assertTrue(mako.equals(fr1.get(0)));
        assertTrue(tako.equals(fr1.get(0)));

        assertFalse(friendsDAO.isFriendship(shako,tako));
        assertFalse(friendsDAO.isFriendship(mako,mako));

    }

    @Test
    @Order(4)
    void removeFriendshipTest() {
        assertTrue(friendsDAO.removeFriendship(tako, mako));
        assertFalse(friendsDAO.isFriendship(tako, mako));
        assertFalse(friendsDAO.removeFriendship(mako, tako));
    }
*/
}

