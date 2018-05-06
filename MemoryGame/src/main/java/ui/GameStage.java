package ui;

import domain.CountryGameBoard;
import domain.GameBoard;
import domain.Score;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameStage {

    private StartMenu startMenuToGoBackto;
    private Button firstMemoryButton;
    private int timer;
    private int penalty;
    private boolean spaceDown;
    private int dimension;
    private GameBoard gameBoard;
    private Label foundNumbers;
    private GridPane playableGp;
    private GridPane blankGp;
    private Label penaltyLabel;
    private Stage primaryStage;
    private int gameType;
    private BorderPane mainSceneBorderpane;
    private String nickname;
    private Label timerLabel;
    private String colorUntouched;
    private String colorTouched;
    private String colorFound;

    /**
     *
     * @param gameType Integer signifying gameType. Defaults to 0 to play with
     * plain integers if invalid.
     * @param dimension Rectangular dimension of the game
     * @param nickname The player's nickname
     * @param startMenutoGoBackTo Start menu in memory
     */
    public GameStage(int gameType, int dimension, String nickname, StartMenu startMenutoGoBackTo) {
        this.colorUntouched = "-fx-base: #ffffff;";
        this.colorFound = "-fx-base: #7FFF00;";
        this.colorTouched = "-fx-base: #FFD700;";
        this.startMenuToGoBackto = startMenutoGoBackTo;
        this.primaryStage = startMenutoGoBackTo.getPrimaryStage();
        this.gameType = gameType;
        this.dimension = dimension;
        this.nickname = nickname;
        this.timer = 0;
        this.firstMemoryButton = newBlankCardButton();
        if (gameType == 1) {
            this.gameBoard = new CountryGameBoard(dimension);
        } else {
            this.gameBoard = new GameBoard(dimension);
        }
    }

    /**
     * Scene providing the graphical user interface for playing the game.
     *
     * @return Scene object
     */
    public Scene gameScene() {
        this.playableGp = playableGp();
        this.blankGp = nakedGp();
        this.mainSceneBorderpane = new BorderPane(playableGp);
        this.timerLabel = newLabelRightSize("0");
        this.penaltyLabel = newLabelRightSize("Recheck penalty: ");
        this.foundNumbers = newLabelRightSize("Found: ");
        this.mainSceneBorderpane.setBottom(this.foundNumbers);
        BorderPane timerBp = new BorderPane();
        timerBp.setRight(timerLabel);
        timerBp.setLeft(penaltyLabel);
        this.mainSceneBorderpane.setTop(timerBp);
        Scene gameScene = new Scene(this.mainSceneBorderpane);
        setUpSceneWithSKeyListeners(gameScene);
        animationTimer().start();

        return gameScene;
    }

    private void setUpSceneWithSKeyListeners(Scene gameScene) {
        gameScene.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.S)) {
                this.spaceDown = true;
                this.mainSceneBorderpane.setCenter(this.blankGp);
            }
        });
        gameScene.setOnKeyReleased((event) -> {
            if (event.getCode().equals(KeyCode.S)) {
                this.spaceDown = false;
                this.mainSceneBorderpane.setCenter(this.playableGp);
            }
        });
    }

    private AnimationTimer animationTimer() {
        AnimationTimer aTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 100000000) {
                    if (gameBoard.foundAllPairs()) {
                        double finalTime = (timer / 10.0);
                        double score = finalTime + gameBoard.getCardCheckedPenalty();
                        primaryStage.setScene(finalScoreScene(nickname, score));
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
        };
        return aTimer;
    }

    private Scene finalScoreScene(String nickname, double score) {
        VBox scoreVbox = new VBox();
        Label scoreLabel = newLabelRightSize("Score: " + score);
        scoreVbox.getChildren().add(scoreLabel);
        TextField nicknameTextField = new TextField(nickname);
        scoreVbox.getChildren().add(nicknameTextField);
        scoreVbox.getChildren().add(new Label("RETURN"));
        BorderPane buttonBorderPane = new BorderPane();
        buttonBorderPane.setLeft(returnToMenuButtonAndSave(nicknameTextField, score));
        buttonBorderPane.setRight(returnToMenuButtonWithoutSaving());
        scoreVbox.getChildren().add(buttonBorderPane);
        return new Scene(scoreVbox);
    }

    private GridPane nakedGp() {
        GridPane gp2 = new GridPane();
        for (int y = 0; y < this.dimension; y++) {
            for (int x = 0; x < this.dimension; x++) {
                Button button = newBlankCardButton();
                button.setText(this.gameBoard.getCardNameFromCard2DArray(x, y));
                gp2.add(button, y, x);
            }
        }
        return gp2;
    }

    private GridPane playableGp() {
        GridPane gp = new GridPane();
        for (int y = 0; y < this.dimension; y++) {
            for (int x = 0; x < this.dimension; x++) {
                Button cardButton = playableCardButton(x, y);
                gp.add(cardButton, y, x);
            }
        }
        return gp;
    }

    private Label newLabelRightSize(String text) {
        Label rightSizeLabel = new Label(text);
        rightSizeLabel.setFont(Font.font(20));
        return rightSizeLabel;
    }

    private Button newBlankCardButton() {
        Button cardButton = new Button();
        cardButton.setFont(Font.font(40));
        cardButton.setMinSize(160, 90);
        cardButton.setMaxSize(160, 90);
        return cardButton;
    }

    private Button playableCardButton(int x, int y) {
        Button playableCardButton = newBlankCardButton();
        playableCardButton.setStyle(colorUntouched);
        Integer xx = x;
        Integer yy = y;
        playableCardButton.setOnMouseClicked((event) -> {
            playableCardButton.setStyle(colorTouched);
            playableCardButton.setText(this.gameBoard.getCardNameFromCard2DArray(x, y));
            if (this.gameBoard.matchingCardInDifferentCoordinate(xx, yy)) {
                playableCardButton.setStyle(colorFound);
                playableCardButton.setText("X");
                firstMemoryButton.setStyle(colorFound);
                firstMemoryButton.setText("X");
                this.foundNumbers.setText(gameBoard.foundPairsString());
                this.firstMemoryButton.setOnMouseClicked(null);
                playableCardButton.setOnMouseClicked(null);

            } else {
                penaltyLabel.setText("Recheck penalty: " + gameBoard.getCardCheckedPenalty());
                if (!this.firstMemoryButton.getText().equals("X")) {
                    this.firstMemoryButton.setText("");
                }
                playableCardButton.setText(gameBoard.getCardNameFromCard2DArray(xx, yy));
                this.firstMemoryButton = playableCardButton;

            }
        }
        );
        return playableCardButton;
    }

    private Button returnToMenuButtonWithoutSaving() {
        Button returnToMenuButton = new Button("WITHOUT SAVING");
        returnToMenuButton.setOnMouseClicked((event) -> {
            this.primaryStage.setScene(startMenuToGoBackto.getScene());
        });
        return returnToMenuButton;
    }

    private Button returnToMenuButtonAndSave(TextField nicknameTextField, double time) {
        Button returnToMenuButton = new Button("AND SAVE SCORE");
        returnToMenuButton.setOnMouseClicked((event) -> {
            try {
                String nickname = nicknameTextField.getText();
                this.startMenuToGoBackto.setNickname(nickname);
                this.startMenuToGoBackto.getDataLogic().addScore(dimension, new Score(nickname, time));
            } catch (Exception ex) {
            }
            this.primaryStage.setScene(startMenuToGoBackto.getScene());
        });
        return returnToMenuButton;
    }

}
