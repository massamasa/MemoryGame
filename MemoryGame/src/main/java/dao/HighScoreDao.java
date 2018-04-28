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
     * HighScoreDao with a preset file name. Handles the connection between the
     * application and an SQLite database containing score data.
     *
     * @throws SQLException
     */
    public HighScoreDao() throws SQLException {
        this.fileName = "HighScores.db";
    }

    /**
     * HighScoreDao with an alternative file name. Handles the connection
     * between the application and an SQLite database containing score data. An
     * alternate filename is necessary for testing
     *
     * @param fileName alternate filename.
     * @throws SQLException
     */
    public HighScoreDao(String fileName) throws SQLException {
        this.fileName = fileName;
    }

    /**
     * Initializes a .db file for storing scores if one is not present. Checks
     * the integrity of the file and tries to create a new .db if it is corrupt
     *
     * @throws SQLException
     * @throws IOException
     */
    public void initializeHighScoreDaoIfNoneOrCorrupt() throws SQLException, IOException {
        if (Files.exists(Paths.get(fileName))) {
            if (!fileHasIntegrity()) {
                deleteOldHighScoreDatabase();
                initializeHighScoreDaoIfNoneOrCorrupt();
            }
            return;
        }
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
        connection.setAutoCommit(false);
        PreparedStatement stmt2 = connection.prepareStatement("CREATE TABLE HighScores2(nickname varchar(8), seconds double)");
        PreparedStatement stmt4 = connection.prepareStatement("CREATE TABLE HighScores4(nickname varchar(8), seconds double)");
        PreparedStatement stmt6 = connection.prepareStatement("CREATE TABLE HighScores6(nickname varchar(8), seconds double)");
        stmt2.executeUpdate();
        stmt4.executeUpdate();
        stmt6.executeUpdate();
        connection.commit();
        stmt2.close();
        stmt4.close();
        stmt6.close();
        connection.close();
    }

    /**
     * Deletes the old .db file if it exists
     *
     * @throws IOException
     */
    public void deleteOldHighScoreDatabase() throws IOException {
        Files.deleteIfExists(Paths.get(fileName));
    }

    /**
     * Adds a score object's nickname and time to the table corresponding to the
     * specified dimension.
     *
     * @param score Score object
     * @param dimension Dimension 2, 4, 6
     */
    public void addScore(Score score, int dimension) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
        String tablename = "HighScores" + dimension;
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + tablename + " (nickname, seconds)  VALUES(?,  ?)");
        stmt.setString(1, score.getNickname());
        stmt.setDouble(2, score.getTime());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    private boolean fileHasIntegrity() throws SQLException {
        try {
            getScores(6);
            getScores(4);
            getScores(2);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @param dimension Dimension 2, 4, 6
     * @return an ArrayList of scores derived from the database table
     * corresponding to the specified dimension
     * @throws SQLException
     */
    public ArrayList<Score> getScores(int dimension) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
        String tablename = "HighScores" + dimension;
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + tablename + " ORDER BY " + tablename + ".seconds");

        ResultSet scoreRs = stmt.executeQuery();
        ArrayList<Score> scoreList = new ArrayList<>();
        while (scoreRs.next()) {
            String nickname = scoreRs.getString("nickname");
            double seconds = scoreRs.getDouble("seconds");
            scoreList.add(new Score(nickname, seconds));
        }
        stmt.close();
        connection.close();
        return scoreList;
    }

}
