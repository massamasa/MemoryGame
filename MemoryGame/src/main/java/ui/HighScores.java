package ui;

import domain.Score;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HighScores {

    private Stage primaryStage;
    private StartMenu startMenuToGoBackTo;
    private int dimension;

    /**
     * Class for controlling the High Score view
     *
     * @param dimension Dimension 2, 4, 6
     * @param startMenuToGoBackTo Start menu
     */
    public HighScores(int dimension, StartMenu startMenuToGoBackTo) {
        this.startMenuToGoBackTo = startMenuToGoBackTo;
        this.primaryStage = this.startMenuToGoBackTo.getPrimaryStage();
        this.dimension = dimension;
    }

    /**
     * Scene that provides a graphical user interface for displaying highscores
     *
     * @return Scene object
     */
    public Scene highScoreScene() {
        GridPane scoreGp = new GridPane();
        ArrayList<Score> scoreList = this.startMenuToGoBackTo.getDataLogic().getScoreList(dimension);
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
            primaryStage.setScene(startMenuToGoBackTo.getScene());
        });
        return returnButton;
    }
}
