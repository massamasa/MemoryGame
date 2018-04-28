package ui;

import domain.DataLogic;
import java.io.IOException;
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
    private TextField nicknameTextArea;
    private DataLogic menuLogic;
    private String gameMode;

    /**
     * Provides a graphical user interface for adjusting game settings, acessing
     * the high score scene and the game stage via a start menu.
     *
     * @param primaryStage
     * @throws SQLException
     * @throws IOException
     */
    public StartMenu(Stage primaryStage) throws SQLException, IOException {
        this.primaryStage = primaryStage;
        this.dimension = 4;
        this.sizeIntegerLabel = new Label("" + dimension);
        this.nicknameTextArea = new TextField("nickname");
        this.menuLogic = new DataLogic();
        this.gameMode = "Plain Integers";
    }
/**
 * Start menu Scene
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
        verticalMenu.getChildren().add(nicknameTextArea);
        verticalMenu.getChildren().add(horizontalMenu);

        Scene scene = new Scene(verticalMenu);

        return scene;
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
            String nickname = nicknameTextArea.getText().substring(0, 8);
            if (this.gameMode.equals("Country Codes")) {
                primaryStage.setScene(new GameStage(primaryStage, 1).gameScene(this.dimension, nickname));
            } else {
                primaryStage.setScene(new GameStage(primaryStage).gameScene(this.dimension, nickname));
            }
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
            } catch (IOException ex) {
                Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return start;
    }

}
