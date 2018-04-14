package ui;

import dao.HighScoreDao;
import domain.Score;
import java.sql.SQLException;
import java.util.ArrayList;

public class CliUiForTesting {

    public static void main(String[] args) throws SQLException {
        HighScoreDao hsDao = new HighScoreDao();
        hsDao.addScore(new Score("a2345678", 900.1), 2);
        ArrayList<Score> scoreList = hsDao.getScores(2);
        scoreList.forEach(score-> System.out.println(score.getNickname() + score.getSeconds()));
        
    }
}
