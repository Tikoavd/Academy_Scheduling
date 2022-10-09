package Reservation;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserve {
    int UserID;
    int ChairID;
    LocalDateTime startDate;
    LocalDateTime endDate;

    public Reserve(int UserID, int ChairId, LocalDateTime startDate, LocalDateTime endDate) {
        this.UserID = UserID;
        this.ChairID = ChairId;
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Reserve(int UserID, int ChairId, Timestamp startDate, Timestamp endDate) {
        this.UserID = UserID;
        this.ChairID = ChairId;
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Reserve(int UserID, int ChairId, String sdate, String stime, String edate, String etime) {
        this.UserID = UserID;
        this.ChairID = ChairId;
        setStartDate(sdate, stime);
        setEndDate(edate, etime);
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public void setChairID(int chairID) {
        ChairID = chairID;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate.toLocalDateTime();
    }

    public void setStartDate(String sdate, String stime) {
        String dateTime = sdate + " " + stime;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startDate = LocalDateTime.parse(dateTime, format);
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate.toLocalDateTime();
    }

    public void setEndDate(String edate, String etime) {
        String dateTime = edate + " " + etime;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.endDate = LocalDateTime.parse(dateTime, format);
    }

    public int getUserID() {
        return UserID;
    }

    public int getChairID() {
        return ChairID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
