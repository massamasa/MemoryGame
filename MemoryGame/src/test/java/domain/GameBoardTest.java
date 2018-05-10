package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {

    private GameBoard gameBoard;
    private int dimension;

    public GameBoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Initialises a GameBoard with a default seed with default card positions
     * Card numbers in this seeded array are 1st row: 2, 1 2nd row: 1, 2
     */
    @Before
    public void setUp() {
        dimension = 2;
        gameBoard = new GameBoard(dimension, 0, 0);
    }

    @After
    public void tearDown() {
    }

    /**
     * If this test fails, other tests dependent on the set position of Cards
     * will fail also.
     */
    @Test
    public void CardsAreInExpectedPosition() {
        assertTrue(this.gameBoard.getCardIntegerFromCard2DArray(0, 0) == 2);
        assertTrue(this.gameBoard.getCardIntegerFromCard2DArray(0, 1) == 1);
        assertTrue(this.gameBoard.getCardIntegerFromCard2DArray(1, 1) == 2);
        assertTrue(this.gameBoard.getCardIntegerFromCard2DArray(1, 0) == 1);
    }

    /**
     * Chooses a card, then chooses the same card and returns false because of
     * the identical coordinates
     */
    @Test
    public void matchingCheckreturnsFalseForTheSameCoordinate() {
        int x = 0;
        int y = 0;
        boolean first = gameBoard.matchingCardInDifferentCoordinate(x, y);
        boolean second = gameBoard.matchingCardInDifferentCoordinate(x, y);
        assertFalse(first);
        assertFalse(second);
    }

    /**
     * Chooses two matching cards in the seeded Card array and checks if pairs
     * can be found. Both cards should be 4.
     */
    @Test
    public void matchingCheckReturnsTrueIfSuccessorEqualsPrevious() {
        boolean first = gameBoard.matchingCardInDifferentCoordinate(1, 0);
        assertTrue(gameBoard.getCard2DArray()[0][1].hasBeenCheckedBefore());
        boolean second = gameBoard.matchingCardInDifferentCoordinate(0, 1);
        assertTrue(gameBoard.getCard2DArray()[1][0].hasBeenCheckedBefore());

        assertFalse(first);
        assertTrue(second);

        assertTrue(gameBoard.getCard2DArray()[0][1].hasBeenFound());
        assertTrue(gameBoard.getCard2DArray()[1][0].hasBeenFound());
        assertEquals("Found: 1, ", this.gameBoard.foundPairsString());
    }

    @Test
    public void penaltyWorks() {
        gameBoard.matchingCardInDifferentCoordinate(0, 0); // top 2
        assertEquals(0, this.gameBoard.getCardCheckedPenalty());
        gameBoard.matchingCardInDifferentCoordinate(0, 1); // bot 1
        assertEquals(0, this.gameBoard.getCardCheckedPenalty());
        gameBoard.matchingCardInDifferentCoordinate(1, 1); // bot 2
        assertEquals(0, this.gameBoard.getCardCheckedPenalty());
        gameBoard.matchingCardInDifferentCoordinate(0, 1); // bot 1
        assertEquals(1, this.gameBoard.getCardCheckedPenalty());
        gameBoard.matchingCardInDifferentCoordinate(1, 0); // top 1
        assertEquals(1, this.gameBoard.getCardCheckedPenalty());
        gameBoard.matchingCardInDifferentCoordinate(1, 1); // bot 2
        assertEquals(2, this.gameBoard.getCardCheckedPenalty());

    }

    @Test
    public void gameCanEndInAllGameModes() {
        for (int i = 0; i <= 3; i++) {
            this.gameBoard = new GameBoard(2, 0, i);
            gameBoard.matchingCardInDifferentCoordinate(0, 0);
            gameBoard.matchingCardInDifferentCoordinate(1, 1);
            gameBoard.matchingCardInDifferentCoordinate(0, 1);
            assertFalse(gameBoard.foundAllPairs());
            gameBoard.matchingCardInDifferentCoordinate(1, 0);
            assertTrue(gameBoard.foundAllPairs());
        }
    }

}
