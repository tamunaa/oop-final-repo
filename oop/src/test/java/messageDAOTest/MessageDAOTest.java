package messageDAOTest;

import dataBase.MessageDAO;
import dataBase.UserDAO;
import objects.User;
import objects.messages.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

import org.apache.commons.dbcp2.BasicDataSource;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class MessageDAOTest {
    private Connection connection;
    private Statement statement;
    private UserDAO userDAO;
    private MessageDAO messageDAO;
    private BasicDataSource dataSource;
    private User user1;
    private User user2;
    private Message note1;
    private Message note2;
    private Message note3;
    private Message req;
    private Message ch;
    private static int id1;
    private static int id2;


    @BeforeAll
    protected static void emptyTables() throws SQLException {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/test_message");
        ds.setUsername("root");
        ds.setPassword("root");
        Connection conn = ds.getConnection();
        PreparedStatement stm = conn.prepareStatement("USE test_message ; ");
        stm.execute();
        stm = conn.prepareStatement("DELETE FROM USERS ;");
        stm.executeUpdate();
        stm = conn.prepareStatement("DELETE FROM MESSAGE ;");
        stm.executeUpdate();
        stm.close();
        conn.close();
    }
    @BeforeEach
    public void setUp() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_message");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        userDAO = new UserDAO(dataSource);
        messageDAO = new MessageDAO(dataSource);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute("USE test_message");
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
/*
    @Test
    void boo() {
        user1 = new User("girl","girl@gmail.com", "password");
        user2 = new User("guy", "guy@gmail.com", "pass");
        id1 = userDAO.addUser(user1);
        id2 = userDAO.addUser(user2);
        List<Message> l = messageDAO.getChat(id1,id2,true);
        assertTrue(l.isEmpty());
        note1 = new NoteMessage(id1,id2,"hey baby,how are you feeling");
        messageDAO.addMessage(note1);
        List<Message> ls = messageDAO.getChat(id1,id2,true);
        assertEquals(1,ls.size());
        assertTrue(messageDAO.deleteMessage(note1.getId()));
        List<Message> ls2 = messageDAO.getChat(id1,id2,true);
        assertTrue(ls2.isEmpty());
    }
 */
    @Test
    @Order(1)
    void createChatAddAndRemoveTest(){
        user1 = new User("girl","girl@gmail.com", "password");
        user2 = new User("guy", "guy@gmail.com", "pass");
         id1 = userDAO.addUser(user1);
         id2 = userDAO.addUser(user2);

         note1 = new NoteMessage(id1,id2,"hey baby,how are you feeling");
         note2 = new NoteMessage(id2,id1,"nothing much, dear");
         note3 = new NoteMessage(id1,id2,"nothing much here either...");

         assertTrue(messageDAO.addMessage(note1));
         assertTrue(messageDAO.addMessage(note2));
         assertTrue(messageDAO.addMessage(note3));
         assertEquals(1,messageDAO.getInteractions(id2).size());
        List<Message> mes = messageDAO.getChat(id1,id2,true);
        List<String> strs = mes.stream().map(x-> (x).getContent()).collect(Collectors.toList());
        assertEquals(3,mes.size());
        assertTrue(strs.contains("hey baby,how are you feeling"));
        assertTrue(strs.contains("nothing much, dear"));
        assertTrue(strs.contains("nothing much here either..."));

    }
    @Test
    @Order(2)
    void deleteConvoTest(){
        assertEquals(1,messageDAO.getInteractions(id1).size());
        assertEquals(3,messageDAO.deleteAllMessages(id1,id2));
        assertTrue(id1 != id2);
        List<Message> mes = messageDAO.getChat(id1,id2,true);
         assertEquals(0, mes.size());
    }

    @Test
    @Order(3)
    void removeAndGetMessagesTest(){
        assertNotEquals(id1, id2);
        assertTrue(id1 != id2);
        ch = new ChallengeMessage(id1,id2,"challengeLink");
        messageDAO.addMessage(ch);
        Message rec = messageDAO.getMessage(ch.getId());
        assertEquals(ch.getSenderID(),rec.getSenderID());
        assertEquals(ch.getRecieverID(),rec.getRecieverID());
        assertEquals(ch.getType(), rec.getType());
        assertTrue(messageDAO.deleteMessage(ch.getId()));
        assertNull(messageDAO.getMessage(ch.getId()));
    }


    @Test
    @Order(4)
    void manageNotificationsTest(){
        User user3 = new User("enemy1","enemy1@gmail.com", "death");
        User user4 = new User("enemy2","enemy2@gmail.com", "death");
        int id3 = userDAO.addUser(user3);
        int id4 = userDAO.addUser(user4);
        ch = new ChallengeMessage(id3,id4,"challengeLink");
        req = new RequestMessage(id3,id4);
        assertTrue(messageDAO.addMessage(ch));
        assertTrue(messageDAO.addMessage(req));
        List<Message> notifs = messageDAO.getUsersRecentIncomingNotifications(id4);
        assertEquals(2,notifs.size());
        for(Message mes : notifs){
            assertTrue(mes.getId() == ch.getId() || mes.getId() == req.getId());
        }
        assertEquals(2,messageDAO.deleteNotifications(id4));
        notifs = messageDAO.getUsersRecentIncomingNotifications(id4);
        assertEquals(0,notifs.size());

    }

}
