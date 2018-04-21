package ui;

import domain.GameBoard;
import domain.MenuLogic;
import domain.Score;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameStage {

    private Button firstMemoryButton;
    private int timer;
    private int penalty;
    private boolean spaceDown;
    private int dimension;
    private GameBoard logic;
    private Label foundNumbers;
    private GridPane gp;
    private GridPane gp2;
    private Label penaltyLabel;
    private Stage primaryStage;
    private Button returnToMenuButton;

    public GameStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene gameScene(int dimension, String nickname) {
        this.dimension = dimension;
        this.timer = 0;
        this.firstMemoryButton = cardButton();
        this.logic = new GameBoard(dimension);
        this.gp = playableGp();
        this.gp2 = nakedGp();
        BorderPane bp = new BorderPane(gp);
        Label timerLabel = new Label("0");
        this.penaltyLabel = new Label("Recheck penalty: ");
        this.foundNumbers = new Label("Found: ");
        bp.setBottom(this.foundNumbers);

        BorderPane timerBp = new BorderPane();
        timerBp.setRight(timerLabel);
        timerBp.setLeft(penaltyLabel);
        bp.setTop(timerBp);
        returnToMenuButton = new Button("RETURN TO MENU");
        returnToMenuButton.setOnMouseClicked((event) -> {
            primaryStage.setScene(new StartMenu(primaryStage).startingScene());
        });
        Scene scene = new Scene(bp);

        scene.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.S)) {
                this.spaceDown = true;
                bp.setCenter(this.gp2);
            }
        });
        scene.setOnKeyReleased((event) -> {
            if (event.getCode().equals(KeyCode.S)) {
                this.spaceDown = false;
                bp.setCenter(this.gp);
            }
        });

        new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 100000000) {
                    if (logic.foundAllPairs()) {
                        double finalTime = (timer / 10.0);
                        double score = finalTime + logic.getCardCheckedPenalty();
                        VBox scoreVbox = new VBox();
                        Label scoreLabel = new Label("Score: " + score);
                        scoreVbox.getChildren().add(scoreLabel);
                        scoreVbox.getChildren().add(returnToMenuButton);
                        bp.setCenter(scoreVbox);
                        try {
                            new MenuLogic().addScore(dimension, new Score(nickname, score));
                        } catch (SQLException ex) {
                            Logger.getLogger(GameStage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        stop();
                    }
                    timerLabel.setText("Base: " + (timer / 10.0));
                    if (spaceDown) {
                        timer += 3;
                    } else {
                        timer += 1;
                    }
                    lastUpdate = now;
                }
            }
        }.start();
        return scene;
    }

    public GridPane nakedGp() {
        GridPane gp2 = new GridPane();
        for (int y = 0; y < this.dimension; y++) {
            for (int x = 0; x < this.dimension; x++) {
                Button button = cardButton();
                button.setText("" + this.logic.getCardIntegerFromCard2DArray(x, y));
                gp2.add(button, y, x);
            }
        }
        return gp2;
    }

    public GridPane playableGp() {
        GridPane gp = new GridPane();
        for (int y = 0; y < this.dimension; y++) {
            for (int x = 0; x < this.dimension; x++) {
                Button button = cardButton();
                button.setText("U");
                Integer xx = x;
                Integer yy = y;
                button.setOnMouseClicked((event) -> {
                    boolean identical = this.logic.identicalCardToPreviousButNotSame(xx, yy);

                    if (identical) {
                        this.firstMemoryButton.setText("F"); //Found
                        button.setText("F");
                        this.foundNumbers.setText(logic.foundPairsString());
                        this.firstMemoryButton.setOnMouseClicked(null);
                        button.setOnMouseClicked(null);

                    } else {

                        penaltyLabel.setText("Recheck penalty: " + logic.getCardCheckedPenalty());

                        if (!this.firstMemoryButton.getText().equals("F")) {
                            this.firstMemoryButton.setText("T");
                        }

                        button.setText("" + logic.getCardIntegerFromCard2DArray(xx, yy));
                        this.firstMemoryButton = button;
                    }
                });
                gp.add(button, y, x);

            }
        }
        return gp;
    }

    public Button cardButton() {
        Button cardButton = new Button();
        cardButton.setFont(Font.font(50));
        cardButton.setMinSize(150, 150);
        cardButton.setMaxSize(150, 150);
        return cardButton;
    }

}
