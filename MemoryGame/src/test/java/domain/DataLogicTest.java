/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mattiost
 */
public class DataLogicTest {

    private DataLogic dataLogic;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.dataLogic = new DataLogic(4, "HighScoresTesting.db");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void canAddScoresAndFetchThem() {
        dataLogic.addScore(4, new Score("matti", 30.0));
        dataLogic.addScore(4, new Score("matti", 40.0));
        dataLogic.addScore(4, new Score("matti", 50.0));
        ArrayList<Score> scoreList = dataLogic.getScoreList(4);
        assertEquals(3, scoreList.size());
    }

}
