package domain;

import dao.HighScoreDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataLogic {

    HighScoreDao hsDao;

    /**
     * Works as a bridge to the highScoreDao. Initializes the HighScoreDao.
     *
     * @throws SQLException
     * @throws IOException
     */
    public DataLogic() throws SQLException, IOException {
        hsDao = new HighScoreDao();
        hsDao.initializeHighScoreDao();
    }

    /**
     * List of scores from the database table corresponding to the dimension
     * specified.
     *
     * @param dimension Dimension 2, 4, 6
     * @return ArrayList of score objects
     * @throws SQLException
     */
    public ArrayList<Score> getScoreList(int dimension) throws SQLException {
        return hsDao.getScores(dimension);
    }

    /**
     * Adds a score score to the database.
     *
     * @param dimension Dimension 2, 4, 6
     * @throws SQLException
     */
    public void addScore(int dimension, Score score) throws SQLException {
        hsDao.addScore(score, dimension);
    }

}
