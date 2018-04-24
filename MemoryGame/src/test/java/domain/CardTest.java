package domain;


import domain.Card;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class CardTest {

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

    @Test
    public void namedCardHasGivenNameAndNumber() {
        Card card = new Card(14, "A8");
        assertEquals("A8", card.getCardName());
        assertEquals(14, card.getCardNumber());
    }

    @Test
    public void CardCanBeChecked() {
        Card card = new Card(1);
        assertFalse(card.isChecked());
        card.setChecked(true);
        assertTrue(card.isChecked());
    }
    
    @Test
    public void CardCanBeFound() {
        Card card = new Card(1);
        assertFalse(card.isFound());
        card.setFound(true);
        assertTrue(card.isFound());
    }

}
