package dao;

import dao.HighScoreDao;
import domain.Score;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HighScoreDaoTest {

    HighScoreDao hsDao;

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
     * @throws IOException
     * @throws SQLException
     */
    @Before
    public void setUp() throws IOException, SQLException {
        hsDao = new HighScoreDao("HighScoresTesting.db");

        hsDao.deleteOldHighScoreDatabase();
        hsDao.initializeHighScoreDaoIfNoneOrCorrupt();
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests adding a score to the database by adding one.
     *
     * @throws SQLException
     */
    @Test
    public void canAddScore() throws SQLException {
        double seconds = 24.9;
        Score score = new Score("THRILLHO", seconds);
        hsDao.addScore(score, 2);
        ArrayList<Score> scores = hsDao.getScores(2);
        assertEquals("THRILLHO", scores.get(0).getNickname());
        assertTrue(scores.get(0).getTime() == seconds);
    }

    /**
     * Tests the sorting of the scores.
     *
     * @throws SQLException
     */
    @Test
    public void scoresSorted() throws SQLException {
        Score scoreSlower = new Score("THRILLHO", 28.9);
        Score scoreFaster = new Score("THRILLHO", 24.9);
        hsDao.addScore(scoreSlower, 2);
        hsDao.addScore(scoreFaster, 2);
        ArrayList<Score> scores = hsDao.getScores(2);
        assertEquals("THRILLHO", scores.get(0).getNickname());
        assertTrue(24.9 == scores.get(0).getTime());
        assertEquals("THRILLHO", scores.get(1).getNickname());
        assertTrue(28.9 == scores.get(1).getTime());
    }

}
