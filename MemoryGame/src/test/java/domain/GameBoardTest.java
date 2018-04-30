package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {

    private GameBoard logic;
    private int dimension;

    public GameBoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dimension = 4;
        logic = new GameBoard(dimension);
    }

    @After
    public void tearDown() {
    }

    /**
     * Chooses a card, then chooses the same card and returns false because of
     * the identical coordinates
     */
    @Test
    public void matchingCheckreturnsFalseForTheSameCoordinate() {
        int x = 0;
        int y = 0;
        boolean first = logic.matchingCardInDifferentCoordinate(x, y);
        boolean second = logic.matchingCardInDifferentCoordinate(x, y);
        assertFalse(first);
        assertFalse(second);
    }

    /**
     *
     * Searches for two matching cards in an array and checks the form of the
     * String returned by foundPairsString()
     */
    @Test
    public void StringBuilderWorks() {

        assertEquals("Found: ", logic.foundPairsString());
        boolean first = false;
        boolean second = false;
        for (int y = 0; y < dimension; y++) {
            for (int x = 1; x < dimension; x++) {
                first = logic.matchingCardInDifferentCoordinate(0, 0);
                second = logic.matchingCardInDifferentCoordinate(x, y);
                if (second) {
                    assertEquals("Found: " + logic.getCardNameFromCard2DArray(0, 0) + ", ", logic.foundPairsString());
                }
            }
        }

    }

    /**
     * Searches for two matching cards in an array and checks if pairs can be
     * found.
     */
    @Test
    public void matchingCheckReturnsTrueIfSuccessorEqualsPrevious() {
        int firstX = 0;
        int firstY = 0;
        int successes = 0;
        boolean first = false;
        boolean second = false;
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                first = logic.matchingCardInDifferentCoordinate(firstX, firstY);
                second = logic.matchingCardInDifferentCoordinate(x, y);
                if (second) {
                    assertEquals(logic.getCardIntegerFromCard2DArray(firstX, firstY), logic.getCardIntegerFromCard2DArray(x, y));
                    successes++;
                }
            }
        }
        assertEquals(1, successes);
    }

}
