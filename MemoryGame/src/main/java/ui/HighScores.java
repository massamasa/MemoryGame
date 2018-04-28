package ui;

import domain.DataLogic;
import domain.Score;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HighScores {

    private Stage primaryStage;

    /**
     * Scene that provides a graphical user interface for displaying highscores
     *
     * @param dimension 2, 4, 6
     * @param primaryStage
     * @return Scene object
     * @throws SQLException
     * @throws IOException
     */
    public Scene highScoreScene(int dimension, Stage primaryStage) throws SQLException, IOException {
        this.primaryStage = primaryStage;
        GridPane scoreGp = new GridPane();
        ArrayList<Score> scoreList = new DataLogic().getScoreList(dimension);
        scoreGp.add(new Label("Nickname"), 0, 0);
        scoreGp.add(new Label("|  Seconds"), 1, 0);
        for (int i = 0; i < scoreList.size(); i++) {
            Score score = scoreList.get(i);
            scoreGp.add(new Label(score.getNickname()), 0, i + 1);
            scoreGp.add(new Label("|  " + score.getTime()), 1, i + 1);

        }
        VBox verticalLayout = new VBox();
        verticalLayout.getChildren().add(scoreGp);
        verticalLayout.getChildren().add(returnButton());
        return new Scene(verticalLayout);
    }

    private Button returnButton() {
        Button returnButton = new Button("RETURN TO MENU");
        returnButton.setOnMouseClicked((event) -> {
            try {
                primaryStage.setScene(new StartMenu(primaryStage).startingScene());
            } catch (SQLException ex) {
                Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return returnButton;
    }
}
