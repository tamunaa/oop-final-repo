import dataBase.AnnouncementDAOSQL;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import objects.Announcement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnnouncementDAOTest {
    private BasicDataSource dataSource;
    private AnnouncementDAOSQL announcementDAO;

    @BeforeEach
    public void setup() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/oopquizzweb");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");
        dataSource = basicDataSource;
        announcementDAO = new AnnouncementDAOSQL(dataSource);
    }

    @AfterEach
    public void tearDown() {
        try {
            dataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertAndGetAnnouncement() {
        Announcement announcement = new Announcement(1, null, "Test Title", "Test Text");
        int insertResult = announcementDAO.insertAnnouncement(announcement);

        assertEquals(AnnouncementDAOSQL.SUCCESS, insertResult);

        Announcement retrievedAnnouncement = announcementDAO.getAnnouncementById(1);

        assertNotNull(retrievedAnnouncement);
        assertEquals("Test Title", retrievedAnnouncement.getTitle());
        assertEquals("Test Text", retrievedAnnouncement.getText());
        assertEquals(1, retrievedAnnouncement.getCreatorId());
    }

    // Other test methods...

    @Test
    public void testGetAllAnnouncements() {
        List<Announcement> initialAnnouncements = announcementDAO.getAllAnnouncements();
        assertEquals(4, initialAnnouncements.size()); // Update this value according to your actual initial data count

        // Rest of the test...
    }
}
