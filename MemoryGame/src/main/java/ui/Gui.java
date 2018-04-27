package ui;

import domain.DataLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {

    /**
     * This is a launcher class for other scene classes.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Memory Game");
        Scene startMenu = new StartMenu(primaryStage).startingScene();
        primaryStage.setScene(startMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
