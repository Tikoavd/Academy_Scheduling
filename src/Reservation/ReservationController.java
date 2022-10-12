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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private Text TextMessage;

    @FXML
    private Text chairNumberText;

    @FXML
    private Text chairTypeText;

    @FXML
    private Button chechButton;

    @FXML
    private ChoiceBox<String> endChoiceTime;

    @FXML
    private TableColumn<Reserve, LocalTime> startColumn;

    @FXML
    private TableColumn<Reserve, LocalTime> endColumn;

    @FXML
    private TableView<Reserve> reservationTable;

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

        if (ReservationController.ChairId == 81) {
            NextButton.setVisible(false);
        }

        if (ReservationController.ChairId == 1) {
            PreviousButton.setVisible(false);
        }

        DBHandler DbCon = new DBHandler();
        ResultSet set = DbCon.getChair(ReservationController.ChairId);

        try {
            if (set.next()) {
                chairTypeText.setText(set.getString(Const.CHAIR_TYPE));
            }
        } catch (SQLException e) {
        }

        set = DbCon.getUserReservation(ReservationController.user.getUserID());

        try {
            if (set.next()) {
                reserveButton.setDisable(true);
                reserveButton.setStyle("-fx-background-color: red");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        set = DbCon.getChairReservations(ChairId, DatePicker.getValue());

        startColumn.setCellValueFactory(new PropertyValueFactory<Reserve, LocalTime>("StartTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Reserve, LocalTime>("EndTime"));

        ObservableList<Reserve> reslist = reservationTable.getItems();

        try {
            while (set.next()) {
                Reserve res = new Reserve(set.getInt(Const.RESERVATION_USER_ID), set.getInt(Const.RESERVATION_CHAIR_ID),
                        set.getTimestamp(Const.RESERVATION_START_DATE), set.getTimestamp(Const.RESERVATION_END_DATE));

                reslist.add(res);
            }
            reservationTable.setItems(reslist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void checkButtonHandler() {
        TextMessage.setText("");
        if(DatePicker.getValue().isBefore(LocalDate.now()) || DatePicker.getValue().isAfter(LocalDate.now().plusWeeks(1))) {
            TextMessage.setText("Can't check for this date");
            TextMessage.setStyle("-fx-fill: red");
            return;
        }

        reservationTable.getItems().clear();

        ObservableList<Reserve> reslist = reservationTable.getItems();

        DBHandler DbCon = new DBHandler();
        ResultSet set = DbCon.getChairReservations(ChairId, DatePicker.getValue());

        try {
            while (set.next()) {
                Reserve res = new Reserve(set.getInt(Const.RESERVATION_USER_ID), set.getInt(Const.RESERVATION_CHAIR_ID),
                        set.getTimestamp(Const.RESERVATION_START_DATE), set.getTimestamp(Const.RESERVATION_END_DATE));

                reslist.add(res);
            }
            reservationTable.setItems(reslist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        Reserve res = new Reserve(ReservationController.user.getUserID(), ReservationController.ChairId,
                DatePicker.getValue().toString(), startChoiceTime.getValue(),
                DatePicker.getValue().toString(), endChoiceTime.getValue());

        if (res.getStartDate().isBefore(LocalDateTime.now()) || res.getStartDate().isAfter(LocalDateTime.now().plusWeeks(1))
                || res.getStartDate().isAfter(res.getEndDate()) || res.getStartDate().isEqual(res.getEndDate())) {
            TextMessage.setText("Please enter correct date.");
            TextMessage.setStyle("-fx-fill: red");
            return;
        }

        DBHandler DbCon = new DBHandler();

        ResultSet set = DbCon.getIntersectedReservations(res);

        try {
            if(set.next()) {
                TextMessage.setText("Already reserved...");
                TextMessage.setStyle("-fx-fill: red");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbCon.addReservation(res);
        reserveButton.setDisable(true);
        reserveButton.setStyle("-fx-background-color: red");
        checkButtonHandler();
        TextMessage.setText("Success!");
        TextMessage.setStyle("-fx-fill: green");
    }
}
