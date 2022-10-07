package GUIMethods;

import Lobby.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class openWindows {
    public static void openWindow(Class className, String srcPath, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(className.getResource(fxmlPath));
            Stage backStage = new Stage();
            backStage.getIcons().add(new Image(Main.class.getResourceAsStream(srcPath + "logo.jpg")));
            backStage.setTitle("Picsart Academy Scheduling");
            backStage.setScene(new Scene(root, 700, 400));
            backStage.setResizable(false);
            backStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
