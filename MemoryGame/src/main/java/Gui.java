
import static java.lang.Thread.sleep;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Gui extends Application {

    private Button firstMemoryButton;
    private int timer;
    private int penalty;
    private boolean spaceDown;
    private int dimension;
    private Logic logic;
    private Label foundNumbers;
    private GridPane gp;
    private GridPane gp2;
    private Label penaltyLabel;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.dimension = 2;
        this.timer = 0;
        this.firstMemoryButton = cardButton();
        this.logic = new Logic(dimension);
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
                        String labelText = "Time: " + finalTime + " + Recheck penalty: " + logic.getCardCheckedPenalty() + " = Score: " + score;
                        bp.setCenter(new Label(labelText));
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

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public GridPane nakedGp() {
        GridPane gp2 = new GridPane();
        for (int y = 0; y < this.dimension; y++) {
            for (int x = 0; x < this.dimension; x++) {
                Button button = cardButton();
                button.setText("" + this.logic.getIntegerFromInteger2DArray(x, y));
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

                        button.setText("" + logic.getIntegerFromInteger2DArray(xx, yy));
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

    public static void main(String[] args) {
        launch(args);
    }

}
