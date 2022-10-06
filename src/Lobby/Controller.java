package Lobby;

import GUIMethods.openWindows;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Controller {
    @FXML
    private Button logInButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userNameField;

    @FXML
    public void handleSignUp(){
        try {
            Parent signUpParent = FXMLLoader.load(getClass().getResource("../SignUp/SignUp.fxml"));
            Stage signUpStage = new Stage();
            signUpStage.getIcons().add(new Image(Main.class.getResourceAsStream("../logo.jpg")));
            signUpStage.setTitle("Picsart Academy Scheduling");
            signUpStage.setScene(new Scene(signUpParent, 700, 400));
            signUpStage.setResizable(false);
            signUpStage.show();
            signUpButton.getScene().getWindow().hide();
        } catch(IOException e) {
        }
    }
}
