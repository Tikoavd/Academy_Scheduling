package Lobby;

import GUIMethods.openWindows;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.io.IOException;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        openWindows.openWindow(getClass(), "../", "Lobby.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
