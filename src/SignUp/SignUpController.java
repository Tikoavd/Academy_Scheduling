package SignUp;

import Lobby.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    @FXML
    private Button backButton;

    @FXML
    private Button createAccountButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField userNameField1;

    @FXML
    private TextField userNameField11;

    @FXML
    private TextField userNameField111;

    @FXML
    private TextField userNameField112;

    @FXML
    void backButtonHandle() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Lobby/Lobby.fxml"));
            Stage backStage = new Stage();
            backStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/logo.jpg" )));
            backStage.setTitle("Picsart Academy Scheduling");
            backStage.setScene(new Scene(root, 700, 400));
            backStage.setResizable(false);
            backStage.show();
            backButton.getScene().getWindow().hide();
        } catch (IOException e) { }
    }

    @FXML
    void createAccountHandle() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Lobby/Lobby.fxml"));
            Stage backStage = new Stage();
            backStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/logo.jpg" )));
            backStage.setTitle("Picsart Academy Scheduling");
            backStage.setScene(new Scene(root, 700, 400));
            backStage.setResizable(false);
            backStage.show();
            createAccountButton.getScene().getWindow().hide();
        } catch (IOException e) { }
    }
}
