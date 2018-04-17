package domain;

import dao.HighScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuLogic {

    HighScoreDao hsDao;

    public MenuLogic() {
        hsDao = new HighScoreDao();
    }

    public ArrayList<Score> getScoreList(int dimension) throws SQLException {
        return hsDao.getScores(dimension);
    }

    public void addScore(int dimension, Score score) throws SQLException {
        hsDao.addScore(score, dimension);
    }

}