
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogicTest {

    Logic logic;
    int dimension;

    public LogicTest() {
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
        logic = new Logic(dimension);
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
                    assertEquals("Found: " + logic.getIntegerFromInteger2DArray(0, 0) + ", ", logic.foundPairsString());
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
                    assertEquals(logic.getIntegerFromInteger2DArray(firstX, firstY), logic.getIntegerFromInteger2DArray(x, y));
                    successes++;
                }
            }
        }
        assertEquals(1, successes);
    }

    @Test
    public void canCreateRectangular2DIntegerArrayWithRightNumbers() {
        int[][] array = logic.createRectangular2DIntegerArray();
        int[] onedimensional = new int[array.length * array[0].length];

        int counter = 0;
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[0].length; x++) {
                onedimensional[counter] = array[y][x];
                counter++;
            }
        }
        Arrays.sort(onedimensional);
        counter = 1;
        for (int i = 0; i < onedimensional.length - 1; i += 2) {
            assertEquals(counter, onedimensional[i]);
            assertEquals(counter, onedimensional[i + 1]);
            counter++;
        }
    }

    @Test
    public void consecutiveNumberPairsGeneratedStartingFromOne() {
        ArrayList<Integer> cardList = logic.createCards();
        int oneUp = 1;
        for (int i = 0; i < cardList.size(); i += 2) {
            Integer integer = oneUp;
            for (int j = 0; j < 2; j++) {
                assertEquals(integer, cardList.get(i + j));
            }
            oneUp++;
        }
    }

}
