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

    public HighScoreDao(String fileName) throws SQLException {
        this.fileName = fileName;
    }

    public void initializeHighScoreDaoIfNone() throws SQLException {
        if (Files.exists(Paths.get(fileName))) {
            return;
        }
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

        PreparedStatement stmt2 = connection.prepareStatement("CREATE TABLE HighScores2(nickname varchar(8), seconds double)");
        PreparedStatement stmt4 = connection.prepareStatement("CREATE TABLE HighScores4(nickname varchar(8), seconds double)");
        PreparedStatement stmt6 = connection.prepareStatement("CREATE TABLE HighScores6(nickname varchar(8), seconds double)");

        stmt2.executeUpdate();
        stmt4.executeUpdate();
        stmt6.executeUpdate();

        stmt2.close();
        stmt4.close();
        stmt6.close();

        connection.close();
    }

    public void deleteOldHighScoreDao() throws IOException {
        Files.deleteIfExists(Paths.get(fileName));
    }

    public void addScore(Score score, int dimension) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+fileName);
        String tablename = "HighScores" + dimension;
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + tablename + " (nickname, seconds)  VALUES(?,  ?)");
        stmt.setString(1, score.getNickname());
        stmt.setDouble(2, score.getSeconds());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public ArrayList<Score> getScores(int dimension) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+fileName);
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
