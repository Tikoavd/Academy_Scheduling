package Lobby;

import Config.Const;
import Config.DBHandler;
import GUIMethods.openWindows;
import MainPage.MainPageController;
import Users.User;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.UnaryOperator;

public class Controller {
    @FXML
    private Text correctLoginText;

    @FXML
    private Text correctPasswordText;

    @FXML
    private Text incorrectUserText;

    @FXML
    private Button logInButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userIdentifierField;

    public void handleSignUp() {
        openWindows.openWindow(getClass(), "../", "../SignUp/SignUp.fxml");
        signUpButton.getScene().getWindow().hide();
    }

    public void handleLogIn() {
        String userIdentifier = userIdentifierField.getText().trim();
        String password = passwordField.getText();

        correctLoginText.setText("");
        correctPasswordText.setText("");
        incorrectUserText.setText("");

        if (userIdentifier.isEmpty() || userIdentifier.length() < 6 || userIdentifier.length() > 20) {
            correctLoginText.setText("Please enter correct login: 6-20 characters.");
            correctLoginText.setStyle("-fx-fill: red");

            if (password.length() < 6 || password.length() > 20 || !passwordField.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
                correctPasswordText.setText("Please enter correct password: 6-20 chatacters(at least 1 number)");
                correctPasswordText.setStyle("-fx-fill: red");
            }
            else {
                correctPasswordText.setText("Password");
                correctPasswordText.setStyle("-fx-fill: green");
            }

            return;
        }
        else {
            correctLoginText.setText("Username, Email or Phone");
            correctLoginText.setStyle("-fx-fill: green");
        }

        if (password.length() < 6 || password.length() > 20 || !passwordField.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
            correctPasswordText.setText("Please enter correct password: 6-20 chatacters(at least 1 number)");
            correctPasswordText.setStyle("-fx-fill: red");
            return;
        }
        else {
            correctPasswordText.setText("Password");
            correctPasswordText.setStyle("-fx-fill: green");
        }

        ResultSet set = (new DBHandler()).logInUser(userIdentifier, password);

        try {
            if (set.next()) {
                User user = new User(set.getString(Const.USER_USERNAME), set.getString(Const.USER_FIRSTNAME), set.getString(Const.USER_LASTNAME),
                        set.getString(Const.USER_EMAIL), set.getString(Const.USER_PHONE), password);
                user.setUserID(set.getInt(Const.USER_ID));

                logInButton.getScene().getWindow().hide();
                (new MainPageController()).createMainPage(user);
            }
            else {
                incorrectUserText.setText("Incorrect Username/Email or password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleUserIdentifier() {
        userIdentifierField.textProperty().addListener(event -> {
            userIdentifierField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !userIdentifierField.getText().isEmpty() &&
                            userIdentifierField.getText().trim().length() < 6 || userIdentifierField.getText().trim().length() > 20);
        });

        userIdentifierField.textProperty().addListener(event -> {
            userIdentifierField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("noterror"),
                    !userIdentifierField.getText().trim().isEmpty() && userIdentifierField.getText().trim().length() >= 6 &&
                            userIdentifierField.getText().trim().length() <= 20);
        });
    }

    public void handlePasswordCorrection() {
        passwordField.textProperty().addListener(event -> {
            passwordField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !passwordField.getText().isEmpty() ||
                    passwordField.getText().length() > 20 || !passwordField.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"));
        });

        passwordField.textProperty().addListener(event -> {
            passwordField.pseudoClassStateChanged(PseudoClass.getPseudoClass("noterror"), !passwordField.getText().isEmpty() &&
                    passwordField.getText().length() <= 20 && passwordField.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"));
        });
    }
}
