package ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartMenu {

    private int dimension;
    private Label sizeIntegerLabel;
    private Stage primaryStage;
    private TextField nicknameTextArea;

    public StartMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.dimension = 4;
        this.sizeIntegerLabel = new Label("" + dimension);
        this.nicknameTextArea = new TextField("nickname");
    }

    public Scene startingScene() {
        VBox verticalMenu = new VBox();
        HBox horizontalMenu = new HBox();
        Label sizeText = new Label("Dimension: ");

        Button minus = minus();
        Button plus = plus();
        Button startButton = startButton();
        Button highScoresButton = highScoresButton();
        horizontalMenu.getChildren().add(sizeText);
        horizontalMenu.getChildren().add(sizeIntegerLabel);
        horizontalMenu.getChildren().add(minus);
        horizontalMenu.getChildren().add(plus);
        horizontalMenu.getChildren().add(startButton);
        
        horizontalMenu.getChildren().add(highScoresButton);
        verticalMenu.getChildren().add(nicknameTextArea);
        verticalMenu.getChildren().add(horizontalMenu);
        

        Scene scene = new Scene(verticalMenu);

        return scene;
    }

    private Button plus() {
        Button plusButton = new Button("+");
        plusButton.setOnMouseClicked((event) -> {
            if (dimension < 6) {
                dimension += 2;
                this.sizeIntegerLabel.setText("" + dimension);
            }
        });
        return plusButton;
    }

    private Button minus() {
        Button minusButton = new Button("-");
        minusButton.setOnMouseClicked((event) -> {
            if (dimension > 2) {
                dimension -= 2;
                this.sizeIntegerLabel.setText("" + dimension);
            }
        });
        return minusButton;
    }

    private Button startButton() {
        Button start = new Button("START");
        start.setOnMouseClicked((event) -> {
            String nickname = nicknameTextArea.getText().substring(0, 8);
            primaryStage.setScene(new GameStage(primaryStage).gameScene(this.dimension, nickname));
        });
        return start;
    }

    private Button highScoresButton() {
        Button start = new Button("HIGH SCORES");
        start.setOnMouseClicked((event) -> {
            String nickname = nicknameTextArea.getText().substring(0, 8);
            try {
                primaryStage.setScene(new HighScores().highScoreScene(dimension, primaryStage));
            } catch (SQLException ex) {
                Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return start;
    }

}
