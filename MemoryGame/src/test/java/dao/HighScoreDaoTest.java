package dao;

import domain.Score;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HighScoreDaoTest {

    private HighScoreDao hsDao;
    private int maxDimension;
    private int minDimension;

    public HighScoreDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Sets up a new database file for testing
     *
     */
    @Before
    public void setUp() {
        this.minDimension = 2;
        this.maxDimension = 10;
        this.hsDao = new HighScoreDao("HighScoresTesting.db");
        this.hsDao.deleteOldHighScoreDatabase();
        this.hsDao.initializeHighScoreDao(10);
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests adding a score to the database by adding one.
     */
    @Test
    public void canAddScoreToMin() {
        double seconds = 24.9;
        Score score = new Score("THRILLHOUSE", seconds);
        hsDao.addScore(score, 2);
        ArrayList<Score> scores = hsDao.getScores(minDimension);
        assertEquals("THRILLHO", scores.get(0).getNickname());
        assertTrue(scores.get(0).getTime() == seconds);
    }

    
    @Test
    public void canAddScoreToMax() {
        double seconds = 240.9;
        Score score = new Score("THRILLHOUSE", seconds);
        hsDao.addScore(score, maxDimension);
        ArrayList<Score> scores = hsDao.getScores(maxDimension);
        assertEquals("THRILLHO", scores.get(0).getNickname());
        assertTrue(scores.get(0).getTime() == seconds);
    }
    /**
     * Tests the sorting of the scores.
     */
    @Test
    public void scoresSorted() {
        Score scoreSlower = new Score("THRILLHOUSE", 28.9);
        Score scoreMiddle = new Score("THRILLHOUSE", 26.9);
        Score scoreFaster = new Score("THRILLHOUSE", 24.9);
        hsDao.addScore(scoreSlower, minDimension);
        hsDao.addScore(scoreFaster, minDimension);
        hsDao.addScore(scoreMiddle, minDimension);
        ArrayList<Score> scores = hsDao.getScores(minDimension);
        assertEquals("THRILLHO", scores.get(0).getNickname());
        assertTrue(24.9 == scores.get(0).getTime());
        assertEquals("THRILLHO", scores.get(1).getNickname());
        assertTrue(26.9 == scores.get(1).getTime());
        assertEquals("THRILLHO", scores.get(2).getNickname());
        assertTrue(28.9 == scores.get(2).getTime());
    }

}
