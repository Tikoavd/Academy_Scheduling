package SignUp;

import Config.DBHandler;
import GUIMethods.openWindows;
import Lobby.Main;
import Users.User;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
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
    private Text validText;

    public void backButtonHandle() {
        openWindows.openWindow(getClass(), "../", "../Lobby/Lobby.fxml");
        backButton.getScene().getWindow().hide();
    }

    public void createAccountHandle() {
        //Please enter valid information

        String userName = userNameField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String eMail = eMailField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String password = passwordField.getText();
        String passwordConfirm = passwordConfirmField.getText();

        if (firstName.isEmpty() ||
                firstName.length() < 3 || firstName.length() > 20
                        || !firstName.matches("^[^0-9]+$")) {
            validText.setText("Please enter valid information!");
            return;
        }

        if (lastName.isEmpty() ||
                lastName.length() < 3 || lastName.length() > 25
                        || !lastName.matches("^[^0-9]+$")) {
            validText.setText("Please enter valid information!");
            return;
        }

        if(userName.isEmpty() ||
                userName.length() < 6 || userName.length() > 20
                        || !userName.matches("^[a-zA-Z0-9]+$")) {
            validText.setText("Please enter valid information!");
            return;
        }

        if (eMail.isEmpty()
                || !eMail.matches("(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
        {
            validText.setText("Please enter valid information!");
            return;
        }

        if (phoneNumber.isEmpty() || phoneNumber.length() != 8 ||
                        !phoneNumber.matches("\\d+")) {
            validText.setText("Please enter valid information!");
            return;
        }

        if (password.isEmpty() ||
                password.length() > 20 || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
            validText.setText("Please enter valid information!");
            return;
        }

        if (passwordConfirm.isEmpty()
                || !passwordConfirm.equals(password)){
            validText.setText("Please enter valid information!");
            return;
        }

        validText.setText("Success!");

        /*User user = new User(userNameField.getText().trim(), firstNameField.getText().trim(), lastNameField.getText().trim(),
                eMailField.getText().trim(), phoneNumberField.getText().trim(), passwordField.getText());

        DBHandler updateDB = new DBHandler();
        updateDB.signUpNewUser(user);*/

        /*openWindows.openWindow(getClass(), "../", "../Lobby/Lobby.fxml");
        createAccountButton.getScene().getWindow().hide();*/
    }

    public void confirmPasswordCorrectly() {
        passwordConfirmField.textProperty().addListener(event -> {
            passwordConfirmField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !passwordConfirmField.getText().isEmpty()
            && !passwordConfirmField.getText().equals(passwordField.getText()));
        });

        passwordConfirmField.textProperty().addListener(event -> {
            passwordConfirmField.pseudoClassStateChanged(PseudoClass.getPseudoClass("noterror"), !passwordConfirmField.getText().isEmpty()
                    && passwordConfirmField.getText().equals(passwordField.getText()));
        });
    }

    public void eMailCorrectly() {
        eMailField.textProperty().addListener(event -> {
            eMailField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !eMailField.getText().isEmpty()
                    && !eMailField.getText().trim().matches("(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"));
        });

        eMailField.textProperty().addListener(event -> {
            eMailField.pseudoClassStateChanged(PseudoClass.getPseudoClass("noterror"), !eMailField.getText().isEmpty()
                    && eMailField.getText().trim().matches("(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"));
        });
    }

    public void firstNameCorrectly() {
        firstNameField.textProperty().addListener(event -> {
            firstNameField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !firstNameField.getText().isEmpty() &&
                            (firstNameField.getText().trim().length() < 3 || firstNameField.getText().trim().length() > 20
                            || !firstNameField.getText().trim().matches("^[^0-9]+$")));
        });

        firstNameField.textProperty().addListener(event -> {
            firstNameField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("noterror"),
                    !firstNameField.getText().trim().isEmpty() && (firstNameField.getText().trim().length() >= 3 &&
                            firstNameField.getText().trim().length() <= 20 && firstNameField.getText().trim().matches("^[^0-9]+$")));
        });
    }

    public void lastNameCorrectly() {
        lastNameField.textProperty().addListener(event -> {
            lastNameField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !lastNameField.getText().isEmpty() &&
                            (lastNameField.getText().trim().length() < 3 || lastNameField.getText().trim().length() > 25
                            || !lastNameField.getText().trim().matches("^[^0-9]+$")));
        });

        lastNameField.textProperty().addListener(event -> {
            lastNameField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("noterror"),
                    !lastNameField.getText().trim().isEmpty() && (lastNameField.getText().trim().length() >= 3 &&
                            lastNameField.getText().trim().length() <= 25 && lastNameField.getText().trim().matches("^[^0-9]+$")));
        });
    }

    public void passwordCorrectly() {
        passwordField.textProperty().addListener(event -> {
            passwordField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !passwordField.getText().isEmpty() &&
                    (passwordField.getText().length() > 20 || !passwordField.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")));
        });

        passwordField.textProperty().addListener(event -> {
            passwordField.pseudoClassStateChanged(PseudoClass.getPseudoClass("noterror"), !passwordField.getText().isEmpty() &&
                    passwordField.getText().length() <= 20 && passwordField.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"));
        });
    }

    public void phoneCorrectly() {
        phoneNumberField.textProperty().addListener(event -> {
            phoneNumberField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !phoneNumberField.getText().isEmpty() &&
                            (phoneNumberField.getText().trim().length() != 8 ||
                            !phoneNumberField.getText().trim().matches("\\d+")));
        });

        phoneNumberField.textProperty().addListener(event -> {
            phoneNumberField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("noterror"),
                    !phoneNumberField.getText().trim().isEmpty() && phoneNumberField.getText().trim().length() == 8 &&
                            phoneNumberField.getText().trim().matches("\\d+"));
        });
    }

    public void userNameCorrectly() {
        userNameField.textProperty().addListener(event -> {
            userNameField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !userNameField.getText().isEmpty() &&
                            (userNameField.getText().trim().length() < 6 || userNameField.getText().trim().length() > 20
                            || !userNameField.getText().trim().matches("^[a-zA-Z0-9]+$")));
        });

        userNameField.textProperty().addListener(event -> {
            userNameField.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("noterror"),
                    !userNameField.getText().trim().isEmpty() && userNameField.getText().trim().length() >= 6 &&
                            userNameField.getText().trim().length() <= 20 && userNameField.getText().trim().matches("^[a-zA-Z0-9]+$"));
        });
    }
}
