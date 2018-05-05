package ui;

import domain.DataLogic;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartMenu {

    private int dimension;
    private Label sizeIntegerLabel;
    private Stage primaryStage;
    private DataLogic menuLogic;
    private String gameMode;
    private Scene scene;
    private String nickname;

    /**
     * Provides a graphical user interface for adjusting game settings, acessing
     * the high score scene and the game stage via a start menu.
     *
     * @param primaryStage
     * @throws SQLException
     * @throws IOException
     */
    public StartMenu(Stage primaryStage, String nickname, int dimension) throws SQLException, IOException {
        this.primaryStage = primaryStage;
        this.nickname = nickname;
        this.dimension = dimension;
        this.sizeIntegerLabel = new Label("" + dimension);
        this.menuLogic = new DataLogic();
        this.gameMode = "Plain Integers";
        this.scene = startingScene();
    }

    /**
     * Start menu Scene
     *
     * @return Scene with start menu functionalities
     */
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
        horizontalMenu.getChildren().add(modeSelectDropdown());
        horizontalMenu.getChildren().add(startButton);

        horizontalMenu.getChildren().add(highScoresButton);
        verticalMenu.getChildren().add(horizontalMenu);

        Scene scene = new Scene(verticalMenu);

        return scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    private ComboBox modeSelectDropdown() {
        ObservableList<String> modeList = FXCollections.observableArrayList(
                "Plain Integers",
                "Country Codes"
        );
        ComboBox modeCBox = new ComboBox(modeList);

        modeCBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            gameMode = (String) newValue;
        });
        return modeCBox;
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
            int gameType = 0; 
            if (this.gameMode.equals("Country Codes")) {
                gameType = 1;
            }
            this.primaryStage.setScene(new GameStage(gameType, this.dimension, nickname, this).gameScene());
            primaryStage.centerOnScreen();
        });
        return start;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private Button highScoresButton() {
        Button start = new Button("HIGH SCORES");
        start.setOnMouseClicked((event) -> {
            try {
                primaryStage.setScene(new HighScores(dimension, this).highScoreScene());
            } catch (Exception ex) {
            }
        });
        return start;
    }

}
