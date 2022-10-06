package SignUp;

import Config.DBHandler;
import GUIMethods.openWindows;
import Lobby.Main;
import Users.User;
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
    private TextField eMailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordConfirmField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField userNameField;

    @FXML
    void backButtonHandle() {
        openWindows.openLobby(getClass(), "../");
        backButton.getScene().getWindow().hide();
    }

    @FXML
    void createAccountHandle() {
        User user = new User(userNameField.getText().trim(), firstNameField.getText().trim(), lastNameField.getText().trim(),
                eMailField.getText().trim(), phoneNumberField.getText().trim());
        String password = passwordField.getText();
        String passwordConfirm = passwordConfirmField.getText();

        signUpUser(user, password);

        openWindows.openLobby(getClass(), "../");
        createAccountButton.getScene().getWindow().hide();
    }

    void signUpUser(User user, String password){
        DBHandler updateDB = new DBHandler();
        updateDB.signUpNewUser(user, password);
    }
}
