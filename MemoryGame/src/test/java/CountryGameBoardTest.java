
import domain.CountryGameBoard;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mattiost
 */
public class CountryGameBoardTest {

    CountryGameBoard countryGB;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        countryGB = new CountryGameBoard(4);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void countryCodesReturnsArrayThatIsLongEnough() {
        assertTrue(countryGB.countryCodes().length >= 36);
    }

    @Test
    public void createdCardListIsCorrectSize() {
        assertEquals(16, countryGB.createCards().size());
    }

}
