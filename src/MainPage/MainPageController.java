package MainPage;

import Config.Const;
import Config.DBHandler;
import GUIMethods.openWindows;
import Lobby.Main;
import Reservation.ReservationController;
import Reservation.Reserve;
import Users.User;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.DbDoc;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Hashtable;

public class MainPageController {
    public static User user;

    ObservableList<String> Times = FXCollections.observableArrayList("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");

    public void createMainPage(User user) {
        MainPageController.user = user;

        DBHandler DbCon = new DBHandler();
        DbCon.deleteOldReservations();

        openWindows.openWindow(getClass(), "../", "../MainPage/mainPage.fxml");
    }

    @FXML
    private void initialize(){
        ChoiceTime.setItems(Times);
        ChoiceTime.setValue(Times.get(12));

        DatePicker.setValue(LocalDate.now());

        ChairButtons = new Button[] {chairNo1, chairNo2, chairNo3, chairNo4, chairNo5, chairNo6, chairNo7, chairNo8, chairNo9, chairNo10,
                chairNo11, chairNo12, chairNo13, chairNo14, chairNo15, chairNo16, chairNo17, chairNo18, chairNo19, chairNo20, chairNo21,
                chairNo22, chairNo23, chairNo24, chairNo25, chairNo26, chairNo27, chairNo28, chairNo29, chairNo30, chairNo31, chairNo32,
                chairNo33, chairNo34, chairNo35, chairNo36, chairNo37, chairNo38, chairNo39, chairNo40, chairNo41, chairNo42, chairNo43,
                chairNo44, chairNo45, chairNo46, chairNo47, chairNo48, chairNo49, chairNo50, chairNo51, chairNo52, chairNo53, chairNo54,
                chairNo55, chairNo56, chairNo57, chairNo58, chairNo59, chairNo60, chairNo61, chairNo62, chairNo63, chairNo64, chairNo65,
                chairNo66, chairNo67, chairNo68, chairNo69, chairNo70, chairNo71, chairNo72, chairNo73, chairNo74, chairNo75, chairNo76,
                chairNo77, chairNo78, chairNo79, chairNo80, chairNo81};

        DBHandler DbCon = new DBHandler();
        ResultSet set = DbCon.getAllReservations(LocalDateTime.now());
        try {
            while (set.next()){
                ChairButtons[set.getInt(Const.RESERVATION_CHAIR_ID) - 1].setStyle("-fx-background-color: red");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reservePane.setVisible(false);

        userNameText.setText(user.getUserName());
        firstNameText.setText(user.getFirstName());
        lastNameText.setText(user.getLastName());
        emailText.setText(user.geteMail());
        phoneText.setText("+374 " + user.getPhoneNumber());

        set = DbCon.getUserReservation(user.getUserID());
        try {
            if (set.next()) {
                Reserve res = new Reserve(set.getInt(Const.RESERVATION_USER_ID), set.getInt(Const.RESERVATION_CHAIR_ID),
                                    set.getTimestamp(Const.RESERVATION_START_DATE), set.getTimestamp(Const.RESERVATION_END_DATE));

                reservePane.setVisible(true);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String startDate = res.getStartDate().format(format);
                String endDate = res.getEndDate().format(format);
                startText.setText("Start: " + startDate);
                endText.setText("End: " + endDate);
                chairNumberText.setText("Chair No: " + res.getChairID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void checkReservation() {
        String dateTimeString = DatePicker.getValue().toString() + " " + ChoiceTime.getValue();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, format);

        if (dateTime.isBefore(LocalDateTime.now()) || dateTime.isAfter(LocalDateTime.now().plusWeeks(1))) {
            CorrectDateText.setText("Please enter correct date");
            return;
        }

        CorrectDateText.setText("");

        for (Button chbutton : ChairButtons) {
            chbutton.setStyle("-fx-background-color: green");
        }

        DBHandler DbCon = new DBHandler();
        ResultSet set = DbCon.getAllReservations(dateTime);
        try {
            while (set.next()){
                ChairButtons[set.getInt(Const.RESERVATION_CHAIR_ID) - 1].setStyle("-fx-background-color: red");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelButtonHandler() {
        DBHandler DbCon = new DBHandler();
        DbCon.deleteUserReservation(user.getUserID());

        reservePane.setVisible(false);
    }

    @FXML
    void logOutButtonHandler() {
        logOutButton.getScene().getWindow().hide();
        openWindows.openWindow(getClass(), "../", "../Lobby/Lobby.fxml");
    }

    @FXML
    private Button cancelButton;

    @FXML
    private Text emailText;

    @FXML
    private Text firstNameText;

    @FXML
    private Text lastNameText;

    @FXML
    private Button logOutButton;

    @FXML
    private Text phoneText;

    @FXML
    private Pane reservePane;

    @FXML
    private Text startText;

    @FXML
    private Text endText;

    @FXML
    private Text chairNumberText;

    @FXML
    private Text userNameText;

    @FXML
    private Button[] ChairButtons;

    @FXML
    private Text CorrectDateText;

    @FXML
    private Button Check;

    @FXML
    private javafx.scene.control.DatePicker DatePicker;

    @FXML
    private ChoiceBox<String> ChoiceTime;

    @FXML
    private Button chairNo1;

    @FXML
    private Button chairNo10;

    @FXML
    private Button chairNo11;

    @FXML
    private Button chairNo12;

    @FXML
    private Button chairNo13;

    @FXML
    private Button chairNo14;

    @FXML
    private Button chairNo15;

    @FXML
    private Button chairNo16;

    @FXML
    private Button chairNo17;

    @FXML
    private Button chairNo18;

    @FXML
    private Button chairNo19;

    @FXML
    private Button chairNo2;

    @FXML
    private Button chairNo20;

    @FXML
    private Button chairNo21;

    @FXML
    private Button chairNo22;

    @FXML
    private Button chairNo23;

    @FXML
    private Button chairNo24;

    @FXML
    private Button chairNo25;

    @FXML
    private Button chairNo26;

    @FXML
    private Button chairNo27;

    @FXML
    private Button chairNo28;

    @FXML
    private Button chairNo29;

    @FXML
    private Button chairNo3;

    @FXML
    private Button chairNo30;

    @FXML
    private Button chairNo31;

    @FXML
    private Button chairNo32;

    @FXML
    private Button chairNo33;

    @FXML
    private Button chairNo34;

    @FXML
    private Button chairNo35;

    @FXML
    private Button chairNo36;

    @FXML
    private Button chairNo37;

    @FXML
    private Button chairNo38;

    @FXML
    private Button chairNo39;

    @FXML
    private Button chairNo4;

    @FXML
    private Button chairNo40;

    @FXML
    private Button chairNo41;

    @FXML
    private Button chairNo42;

    @FXML
    private Button chairNo43;

    @FXML
    private Button chairNo44;

    @FXML
    private Button chairNo45;

    @FXML
    private Button chairNo46;

    @FXML
    private Button chairNo47;

    @FXML
    private Button chairNo48;

    @FXML
    private Button chairNo49;

    @FXML
    private Button chairNo5;

    @FXML
    private Button chairNo50;

    @FXML
    private Button chairNo51;

    @FXML
    private Button chairNo52;

    @FXML
    private Button chairNo53;

    @FXML
    private Button chairNo54;

    @FXML
    private Button chairNo55;

    @FXML
    private Button chairNo56;

    @FXML
    private Button chairNo57;

    @FXML
    private Button chairNo58;

    @FXML
    private Button chairNo59;

    @FXML
    private Button chairNo6;

    @FXML
    private Button chairNo60;

    @FXML
    private Button chairNo61;

    @FXML
    private Button chairNo62;

    @FXML
    private Button chairNo63;

    @FXML
    private Button chairNo64;

    @FXML
    private Button chairNo65;

    @FXML
    private Button chairNo66;

    @FXML
    private Button chairNo67;

    @FXML
    private Button chairNo68;

    @FXML
    private Button chairNo69;

    @FXML
    private Button chairNo7;

    @FXML
    private Button chairNo70;

    @FXML
    private Button chairNo71;

    @FXML
    private Button chairNo72;

    @FXML
    private Button chairNo73;

    @FXML
    private Button chairNo74;

    @FXML
    private Button chairNo75;

    @FXML
    private Button chairNo76;

    @FXML
    private Button chairNo77;

    @FXML
    private Button chairNo78;

    @FXML
    private Button chairNo79;

    @FXML
    private Button chairNo8;

    @FXML
    private Button chairNo80;

    @FXML
    private Button chairNo81;

    @FXML
    private Button chairNo9;

    @FXML
    void handleChairNo1() {
        chairNo1.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 1);
    }

    @FXML
    void handleChairNo10() {
        chairNo10.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 10);
    }

    @FXML
    void handleChairNo11() {
        chairNo11.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 11);
    }

    @FXML
    void handleChairNo12() {
        chairNo12.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 12);
    }

    @FXML
    void handleChairNo13() {
        chairNo13.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 13);
    }

    @FXML
    void handleChairNo14() {
        chairNo14.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 14);
    }

    @FXML
    void handleChairNo15() {
        chairNo15.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 15);
    }

    @FXML
    void handleChairNo16() {
        chairNo16.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 16);
    }

    @FXML
    void handleChairNo17() {
        chairNo17.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 17);
    }

    @FXML
    void handleChairNo18() {
        chairNo18.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 18);
    }

    @FXML
    void handleChairNo19() {
        chairNo19.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 19);
    }

    @FXML
    void handleChairNo2() {
        chairNo2.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 2);
    }

    @FXML
    void handleChairNo20() {
        chairNo20.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 20);
    }

    @FXML
    void handleChairNo21() {
        chairNo21.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 21);
    }

    @FXML
    void handleChairNo22() {
        chairNo22.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 22);
    }

    @FXML
    void handleChairNo23() {
        chairNo23.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 23);
    }

    @FXML
    void handleChairNo24() {
        chairNo24.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 24);
    }

    @FXML
    void handleChairNo25() {
        chairNo25.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 25);
    }

    @FXML
    void handleChairNo26() {
        chairNo26.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 26);
    }

    @FXML
    void handleChairNo27() {
        chairNo27.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 27);
    }

    @FXML
    void handleChairNo28() {
        chairNo28.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 28);
    }

    @FXML
    void handleChairNo29() {
        chairNo29.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 29);
    }

    @FXML
    void handleChairNo3() {
        chairNo3.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 3);
    }

    @FXML
    void handleChairNo30() {
        chairNo30.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 30);
    }

    @FXML
    void handleChairNo31() {
        chairNo31.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 31);
    }

    @FXML
    void handleChairNo32() {
        chairNo32.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 32);
    }

    @FXML
    void handleChairNo33() {
        chairNo33.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 33);
    }

    @FXML
    void handleChairNo34() {
        chairNo34.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 34);
    }

    @FXML
    void handleChairNo35() {
        chairNo35.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 35);
    }

    @FXML
    void handleChairNo36() {
        chairNo36.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 36);
    }

    @FXML
    void handleChairNo37() {
        chairNo37.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 37);
    }

    @FXML
    void handleChairNo38() {
        chairNo38.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 38);
    }

    @FXML
    void handleChairNo39() {
        chairNo39.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 39);
    }

    @FXML
    void handleChairNo4() {
        chairNo4.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 4);
    }

    @FXML
    void handleChairNo40() {
        chairNo40.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 40);
    }

    @FXML
    void handleChairNo41() {
        chairNo41.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 41);
    }

    @FXML
    void handleChairNo42() {
        chairNo42.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 42);
    }

    @FXML
    void handleChairNo43() {
        chairNo43.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 43);
    }

    @FXML
    void handleChairNo44() {
        chairNo44.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 44);
    }

    @FXML
    void handleChairNo45() {
        chairNo45.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 45);
    }

    @FXML
    void handleChairNo46() {
        chairNo46.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 46);
    }

    @FXML
    void handleChairNo47() {
        chairNo47.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 47);
    }

    @FXML
    void handleChairNo48() {
        chairNo48.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 48);
    }

    @FXML
    void handleChairNo49() {
        chairNo49.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 49);
    }

    @FXML
    void handleChairNo5() {
        chairNo5.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 5);
    }

    @FXML
    void handleChairNo50() {
        chairNo50.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 50);
    }

    @FXML
    void handleChairNo51() {
        chairNo51.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 51);
    }

    @FXML
    void handleChairNo52() {
        chairNo52.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 52);
    }

    @FXML
    void handleChairNo53() {
        chairNo53.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 53);
    }

    @FXML
    void handleChairNo54() {
        chairNo54.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 54);
    }

    @FXML
    void handleChairNo55() {
        chairNo55.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 55);
    }

    @FXML
    void handleChairNo56() {
        chairNo56.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 56);
    }

    @FXML
    void handleChairNo57() {
        chairNo57.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 57);
    }

    @FXML
    void handleChairNo58() {
        chairNo58.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 58);
    }

    @FXML
    void handleChairNo59() {
        chairNo59.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 59);
    }

    @FXML
    void handleChairNo6() {
        chairNo6.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 6);
    }

    @FXML
    void handleChairNo60() {
        chairNo60.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 60);
    }

    @FXML
    void handleChairNo61() {
        chairNo61.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 61);
    }

    @FXML
    void handleChairNo62() {
        chairNo62.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 62);
    }

    @FXML
    void handleChairNo63() {
        chairNo63.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 63);
    }

    @FXML
    void handleChairNo64() {
        chairNo64.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 64);
    }

    @FXML
    void handleChairNo65() {
        chairNo65.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 65);
    }

    @FXML
    void handleChairNo66() {
        chairNo66.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 66);
    }

    @FXML
    void handleChairNo67() {
        chairNo67.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 67);
    }

    @FXML
    void handleChairNo68() {
        chairNo68.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 68);
    }

    @FXML
    void handleChairNo69() {
        chairNo69.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 69);
    }

    @FXML
    void handleChairNo7() {
        chairNo7.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 7);
    }

    @FXML
    void handleChairNo70() {
        chairNo70.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 70);
    }

    @FXML
    void handleChairNo71() {
        chairNo71.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 71);
    }

    @FXML
    void handleChairNo72() {
        chairNo72.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 72);
    }

    @FXML
    void handleChairNo73() {
        chairNo73.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 73);
    }

    @FXML
    void handleChairNo74() {
        chairNo74.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 74);
    }

    @FXML
    void handleChairNo75() {
        chairNo75.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 75);
    }

    @FXML
    void handleChairNo76() {
        chairNo76.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 76);
    }

    @FXML
    void handleChairNo77() {
        chairNo77.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 77);
    }

    @FXML
    void handleChairNo78() {
        chairNo78.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 78);
    }

    @FXML
    void handleChairNo79() {
        chairNo79.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 79);
    }

    @FXML
    void handleChairNo8() {
        chairNo8.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 8);
    }

    @FXML
    void handleChairNo80() {
        chairNo80.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 80);
    }

    @FXML
    void handleChairNo81() {
        chairNo81.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 81);
    }

    @FXML
    void handleChairNo9() {
        chairNo9.getScene().getWindow().hide();
        (new ReservationController()).createReservationPage(MainPageController.user, 9);
    }
}
