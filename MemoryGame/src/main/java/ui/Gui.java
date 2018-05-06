package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {

    /**
     * This is a launcher class for other scene classes.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Memory Game");
        Scene startMenu = new StartMenu(primaryStage, "nickname", 4, 8).getScene();
        primaryStage.setScene(startMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
