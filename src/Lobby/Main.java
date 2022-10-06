package Lobby;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/logo.jpg" )));
        primaryStage.setTitle("Picsart Academy Scheduling");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
