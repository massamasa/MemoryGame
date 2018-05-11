package domain;

import dao.HighScoreDao;
import java.util.ArrayList;

public class DataLogic {

    private HighScoreDao hsDao;
    private int dimension;

    /**
     * Works as a bridge to the highScoreDao. Initialises the HighScoreDao.
     */
    public DataLogic(int maxDimension, String fileName) {
        hsDao = new HighScoreDao(fileName);
        hsDao.initializeHighScoreDao(maxDimension);
    }

    /**
     * List of scores from the database table corresponding to the dimension
     * specified.
     *
     * @param dimension
     * @return ArrayList of Score objects
     */
    public ArrayList<Score> getScoreList(int dimension) {
        return hsDao.getScores(dimension);
    }

    /**
     * Adds a score score to the database.
     *
     * @param dimension Dimension
     * @param score Score object to add
     */
    public void addScore(int dimension, Score score) {
        hsDao.addScore(score, dimension);
    }

}
