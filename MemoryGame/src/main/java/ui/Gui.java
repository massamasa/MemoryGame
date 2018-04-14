package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {

    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene startMenu = new StartMenu(primaryStage).startingScene();
        primaryStage.setScene(startMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
