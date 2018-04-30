package domain;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;

public class ScoreTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests an empty nickname and expects the set nickname String to contain
     * "ANON".
     */
    public void nicknameLength0() {
        Score score = new Score("", 2.0);
        assertEquals("ANON", score.getNickname());
        assertTrue(2.0 == score.getTime());
    }

    /**
     * Tests a 7 character nickname and expects the set nickname to contain an
     * identical String that is 7 characters long.
     */
    public void nicknameLength7() {
        Score score = new Score("THRILLH", 2.0);
        assertEquals("THRILLH", score.getNickname());
    }

    /**
     * Tests an 8 character nickname and expects the set nickname to contain an
     * identical String that is 8 characters long.
     */
    public void nicknameLength8() {
        Score score = new Score("THRILLHO", 2.0);
        assertEquals("THRILLHO", score.getNickname());
    }

    /**
     * Tests a 9 character nickname and expects the set nickname to contain the
     * first 8 characters of the String.
     */
    public void nicknameLength9() {
        Score score = new Score("THRILLHOU", 2.0);
        assertEquals("THRILLHO", score.getNickname());
    }

    /**
     * Tests a 12 character nickname and expects the set nickname to contain the
     * first 8 characters of the String.
     */
    public void nicknameLength12() {
        Score score = new Score("THRILLHOUSE", 2.0);
        assertEquals("THRILLHO", score.getNickname());
    }
}
