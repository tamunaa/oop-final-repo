import dataBase.AnnouncementDAOSQL;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import objects.Announcement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class AnnouncementDAOTest {
    private BasicDataSource dataSource;
    private AnnouncementDAOSQL announcementDAO;


    @BeforeEach
    public void setup() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_announcements");
        dataSource.setUsername("root");
        dataSource.setPassword("root:root");
        announcementDAO = new AnnouncementDAOSQL(dataSource);
    }
    @AfterEach
    public void tearDown() {
        try {
            dataSource.close();
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

    @Test
    public void testDeleteAnnouncement() {
        Announcement announcement = new Announcement(1, null, "Delete Test", "This announcement will be deleted");
        announcementDAO.insertAnnouncement(announcement);

        int deleteResult = announcementDAO.deleteAnnouncementById(1);

        assertEquals(AnnouncementDAOSQL.SUCCESS, deleteResult);

        Announcement retrievedAnnouncement = announcementDAO.getAnnouncementById(1);
        assertNull(retrievedAnnouncement);
    }

    @Test
    public void testGetAllAnnouncements() {
        List<Announcement> initialAnnouncements = announcementDAO.getAllAnnouncements();
        assertEquals(0, initialAnnouncements.size());

        Announcement announcement1 = new Announcement(1, null, "Announcement 1", "Text 1");
        Announcement announcement2 = new Announcement(2, null, "Announcement 2", "Text 2");

        announcementDAO.insertAnnouncement(announcement1);
        announcementDAO.insertAnnouncement(announcement2);

        List<Announcement> retrievedAnnouncements = announcementDAO.getAllAnnouncements();
        assertEquals(2, retrievedAnnouncements.size());
    }

    @Test
    public void testSearchAnnouncements() {
        Announcement announcement1 = new Announcement(1, null, "Important Update", "System maintenance scheduled");
        Announcement announcement2 = new Announcement(2, null, "New Feature", "Introducing advanced functionality");
        announcementDAO.insertAnnouncement(announcement1);
        announcementDAO.insertAnnouncement(announcement2);

        List<Announcement> searchResults = announcementDAO.searchAnnouncements("Feature");
        assertEquals(1, searchResults.size());
        assertEquals("New Feature", searchResults.get(0).getTitle());
    }
}
