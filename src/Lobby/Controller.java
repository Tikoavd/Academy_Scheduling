package Lobby;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void handleSignUp(){
        System.out.println("Hello World!");
    }
}
