package ChallengeDAOTests;

import dataBase.ChallengeDAO;
import dataBase.DbChallengeDAO;
import objects.Challenge;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ChallengeDAOTest {
    private ChallengeDAO challengeDAO;
    private BasicDataSource dataSource;

    @BeforeEach
    void setUp() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_challenge");
        dataSource.setUsername("root");
        dataSource.setPassword("123456789");

        challengeDAO = new DbChallengeDAO(dataSource);
    }


    @Test
    void addChallenge() {
//        Challenge challenge = new Challenge(2, 3, 4);
//        assertEquals(challengeDAO.addChallenge(challenge), challenge.getChallengeId());
//
        Challenge challenge = new Challenge(3, 1, 2);
        assertEquals(challengeDAO.addChallenge(challenge), challenge.getChallengeId());
//
//        challenge = new Challenge(1, 2, 6);
//        assertEquals(challengeDAO.addChallenge(challenge), challenge.getChallengeId());

//        challenge = new Challenge(3, 2, 1);
//        assertEquals(challengeDAO.addChallenge(challenge), challenge.getChallengeId());
    }

    @Test
    void removeChallenge() {
        assertNotEquals(null, challengeDAO.getChallengeByID(10));
        assertTrue(challengeDAO.removeChallenge(10));
        assertNull(challengeDAO.getChallengeByID(10));
    }

    @Test
    void getChallengeByChallengerID() {
        List<Challenge> challenges = challengeDAO.getChallengeByChallengerID(1);
        assertEquals(3, challenges.size());

        assertEquals(1, challenges.get(2).getChallengeId());
        assertEquals(1, challenges.get(2).getChallengerID());
        assertEquals(3, challenges.get(2).getChallengedID());
        assertEquals(3, challenges.get(2).getQuizID());

        assertEquals(3, challenges.get(1).getChallengeId());
        assertEquals(1, challenges.get(1).getChallengerID());
        assertEquals(3, challenges.get(1).getChallengedID());
        assertEquals(2, challenges.get(1).getQuizID());

        assertEquals(7, challenges.get(0).getChallengeId());
        assertEquals(1, challenges.get(0).getChallengerID());
        assertEquals(2, challenges.get(0).getChallengedID());
        assertEquals(6, challenges.get(0).getQuizID());
    }

    @Test
    void getChallengeByChallengedID() {
        List<Challenge> challenges = challengeDAO.getChallengeByChallengedID(2);
        assertEquals(2, challenges.size());

        assertEquals(7, challenges.get(1).getChallengeId());
        assertEquals(1, challenges.get(1).getChallengerID());
        assertEquals(2, challenges.get(1).getChallengedID());
        assertEquals(6, challenges.get(1).getQuizID());

        assertEquals(4, challenges.get(0).getChallengeId());
        assertEquals(3, challenges.get(0).getChallengerID());
        assertEquals(2, challenges.get(0).getChallengedID());
        assertEquals(1, challenges.get(0).getQuizID());
    }

    @Test
    void getChallengeByQuizID() {
        List<Challenge> challenges = challengeDAO.getChallengeByQuizID(5);
        assertEquals(1, challenges.size());

        assertEquals(2, challenges.get(0).getChallengeId());
        assertEquals(2, challenges.get(0).getChallengerID());
        assertEquals(1, challenges.get(0).getChallengedID());
        assertEquals(5, challenges.get(0).getQuizID());
    }

    @Test
    void getChallengeByID(){
        Challenge challenge = challengeDAO.getChallengeByID(1);
        assertEquals(1, challenge.getChallengeId());
        assertEquals(1, challenge.getChallengerID());
        assertEquals(3, challenge.getChallengedID());
        assertEquals(3, challenge.getQuizID());
    }
}
