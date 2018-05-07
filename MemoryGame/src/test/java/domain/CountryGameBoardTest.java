package domain;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CountryGameBoardTest {

    private GameBoard cGB;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.cGB = new CountryGameBoard(2, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void canCreateCountryGameBoardWithStringNamesButMatchingNumbers() {
        String cardNameFromCard2DArray = cGB.getCardNameFromCard2DArray(1, 1);
        assertEquals("DZ", cardNameFromCard2DArray);
        int firstCardNumberFrom2DArray = cGB.getCardIntegerFromCard2DArray(0, 0);
        int secondCardNumberFrom2DArray = cGB.getCardIntegerFromCard2DArray(1, 1);
        assertTrue(firstCardNumberFrom2DArray == secondCardNumberFrom2DArray);
    }
}
