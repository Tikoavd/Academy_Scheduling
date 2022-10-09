package Reservation;

import Config.Const;
import Config.DBHandler;
import GUIMethods.openWindows;
import MainPage.MainPageController;
import Users.User;
import com.mysql.cj.xdevapi.DbDoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationController {
    public static User user;
    public static int ChairId;

    ObservableList<String> Times = FXCollections.observableArrayList("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");

    public void createReservationPage(User user, int ChairId) {
        ReservationController.user = user;
        ReservationController.ChairId = ChairId;

        openWindows.openReservation(getClass(), "../", "../Reservation/ReservationPage.fxml");
    }

    @FXML
    private DatePicker DatePicker;

    @FXML
    private Button HomeButton;

    @FXML
    private Button NextButton;

    @FXML
    private Button PreviousButton;

    @FXML
    private Text chairNumberText;

    @FXML
    private Text chairTypeText;

    @FXML
    private Button chechButton;

    @FXML
    private ChoiceBox<String> endChoiceTime;

    @FXML
    private TableColumn<?, ?> reservationColumn;

    @FXML
    private TableView<?> reservationTable;

    @FXML
    private Button reserveButton;

    @FXML
    private ChoiceBox<String> startChoiceTime;

    @FXML
    private void initialize() {
        startChoiceTime.setItems(Times);
        startChoiceTime.setValue(Times.get(12));

        endChoiceTime.setItems(Times);
        endChoiceTime.setValue(Times.get(13));

        DatePicker.setValue(LocalDate.now());

        chairNumberText.setText(Integer.toString(ReservationController.ChairId));

        if(ReservationController.ChairId == 81) {
            NextButton.setVisible(false);
        }

        if(ReservationController.ChairId == 1) {
            PreviousButton.setVisible(false);
        }

        DBHandler DbCon = new DBHandler();
        ResultSet set = DbCon.getChair(ReservationController.ChairId);

        try {
            if(set.next()){
                chairTypeText.setText(set.getString(Const.CHAIR_TYPE));
            }
        } catch (SQLException e) {
        }
    }

    @FXML
    void checkButtonHandler() {

    }

    @FXML
    void homeButtonHandler() {
        HomeButton.getScene().getWindow().hide();
        (new MainPageController()).createMainPage(ReservationController.user);
    }

    @FXML
    void nextButtonHandler() {
        NextButton.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, ReservationController.ChairId + 1);
    }

    @FXML
    void previousButtonHandler() {
        PreviousButton.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, ReservationController.ChairId - 1);
    }

    @FXML
    void reserveButtonHandler() {

    }
}
