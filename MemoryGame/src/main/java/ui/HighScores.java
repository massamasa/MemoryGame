/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.MenuLogic;
import domain.Score;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HighScores {

    private Stage primaryStage;

    public Scene highScoreScene(int dimension, Stage primaryStage) throws SQLException {
        this.primaryStage = primaryStage;
        GridPane scoreGp = new GridPane();
        ArrayList<Score> scoreList = new MenuLogic().getScoreList(dimension);
        scoreGp.add(new Label("Nickname"), 0, 0);
        scoreGp.add(new Label("|  Seconds"), 1, 0);
        for (int i = 0; i < scoreList.size(); i++) {
            Score score = scoreList.get(i);
            scoreGp.add(new Label(score.getNickname()), 0, i + 1);
            scoreGp.add(new Label("|  " + score.getSeconds()), 1, i + 1);

        }
        VBox verticalLayout = new VBox();
        verticalLayout.getChildren().add(scoreGp);
        verticalLayout.getChildren().add(returnButton());
        return new Scene(verticalLayout);
    }

    private Button returnButton() {
        Button returnButton = new Button("RETURN TO MENU");
        returnButton.setOnMouseClicked((event) -> {
            primaryStage.setScene(new StartMenu(primaryStage).startingScene());
        });
        return returnButton;
    }
}
