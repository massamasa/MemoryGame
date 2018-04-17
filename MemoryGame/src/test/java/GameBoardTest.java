
import domain.GameBoard;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {

    GameBoard logic;
    int dimension;

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

    @Test
    public void identicalCheckreturnsFalseForTheSameCoordinate() {
        int x = 0;
        int y = 0;
        boolean first = logic.identicalCardToPreviousButNotSame(x, y);
        boolean second = logic.identicalCardToPreviousButNotSame(x, y);
        assertFalse(first);
        assertFalse(second);
    }

    @Test
    public void StringBuilderWorks() {
        
        assertEquals("Found: ", logic.foundPairsString());
        boolean first = false;
        boolean second = false;
        for (int y = 0; y < dimension; y++) {
            for (int x = 1; x < dimension; x++) {
                first = logic.identicalCardToPreviousButNotSame(0, 0);
                second = logic.identicalCardToPreviousButNotSame(x, y);
                if (second) {
                    assertEquals("Found: " + logic.getCardIntegerFromCard2DArray(0, 0) + ", ", logic.foundPairsString());
                }
            }
        }

    }

    @Test
    public void identicalCheckreturnsTrueIfSuccessorEqualsPrevious() {
        int firstX = 0;
        int firstY = 0;
        int successes = 0;
        boolean first = false;
        boolean second = false;
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                first = logic.identicalCardToPreviousButNotSame(firstX, firstY);
                second = logic.identicalCardToPreviousButNotSame(x, y);
                if (second) {
                    assertEquals(logic.getCardIntegerFromCard2DArray(firstX, firstY), logic.getCardIntegerFromCard2DArray(x, y));
                    successes++;
                }
            }
        }
        assertEquals(1, successes);
    }

    

}
