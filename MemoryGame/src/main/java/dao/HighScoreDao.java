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

    public void initializeHighScoreDao() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:HighScores.db");
        PreparedStatement stmt = connection.prepareStatement("CREATE TABLE HighScores2(nickname varchar(8), seconds double);\n"
                + "CREATE TABLE HighScores4(nickname varchar(8), seconds double);\n"
                + "CREATE TABLE HighScores6(nickname varchar(8), seconds double);");
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public void deleteOldHighScoreDao() throws IOException {
        Files.delete(Paths.get("HighScores.db"));
    }

    public void addScore(Score score, int dimension) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:HighScores.db");
        String tablename = "HighScores" + dimension;
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + tablename + " (nickname, seconds)  VALUES(?,  ?)");
        stmt.setString(1, score.getNickname());
        stmt.setDouble(2, score.getSeconds());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public ArrayList<Score> getScores(int dimension) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:HighScores.db");
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
