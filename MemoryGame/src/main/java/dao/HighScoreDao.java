package dao;

import domain.Score;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HighScoreDao {

    private String fileName;

    /**
     * HighScoreDao with an alternative file name. Handles the connection
     * between the application and an SQLite database containing score data. An
     * alternate filename is necessary for testing
     *
     * @param fileName alternate filename.
     */
    public HighScoreDao(String fileName) {
        this.fileName = fileName;
    }

    /**
     *
     * Initialises a .db file for storing scores if one is not present.
     *
     * @param maxDimension The maximum rectangular dimensions the game can be
     * played with.
     */
    public void initializeHighScoreDao(int maxDimension) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            connection.setAutoCommit(false);
            for (int i = 2; i <= maxDimension; i += 2) {
                PreparedStatement stmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS HighScores" + i + " (nickname varchar(8), seconds double)");
                stmt.executeUpdate();
                stmt.close();
            }
            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Deletes the old .db file if it exists.
     */
    public void deleteOldHighScoreDatabase() {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Adds a score object's nickname and time to the table corresponding to the
     * specified dimension.
     *
     * @param score Score object
     * @param dimension Dimension of the tab
     */
    public void addScore(Score score, int dimension) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            String tablename = "HighScores" + dimension;
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + tablename + " (nickname, seconds)  VALUES(?,  ?)");
            stmt.setString(1, score.getNickname());
            stmt.setDouble(2, score.getTime());
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * @param dimension Played dimension of scores in table in database
     * @return an ArrayList of scores derived from the database table
     * corresponding to the specified dimension
     */
    public ArrayList<Score> getScores(int dimension) {
        ArrayList<Score> scoreList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            String tablename = "HighScores" + dimension;
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + tablename + " ORDER BY " + tablename + ".seconds");

            ResultSet scoreRs = stmt.executeQuery();

            while (scoreRs.next()) {
                String nickname = scoreRs.getString("nickname");
                double seconds = scoreRs.getDouble("seconds");
                scoreList.add(new Score(nickname, seconds));
            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return scoreList;
    }

}
