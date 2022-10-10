package Config;

import Reservation.Reserve;
import Users.User;

import java.sql.*;

public class DBHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" +
                dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpNewUser(User user) {
        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" +
                Const.USER_USERNAME + "," + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME +
                "," + Const.USER_EMAIL + "," + Const.USER_PHONE + "," + Const.USER_PASSWORD + ")" +
                " VALUES(?,?,?,?,?," + "MD5(?))";

        try {
            PreparedStatement prepStat = getDbConnection().prepareStatement(insert);
            prepStat.setString(1, user.getUserName());
            prepStat.setString(2, user.getFirstName());
            prepStat.setString(3, user.getLastName());
            prepStat.setString(4, user.geteMail());
            prepStat.setString(5, user.getPhoneNumber());
            prepStat.setString(6, user.getPassword());

            prepStat.executeUpdate();
        } catch (SQLException e) {
        } catch (ClassNotFoundException e){
        }
    }

    public ResultSet logInUser(String userIdentifier, String password) {
        ResultSet set = null;

        String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE (" +
                Const.USER_USERNAME + "=\"" + userIdentifier + "\"" + " OR " +
                Const.USER_EMAIL + "=\"" + userIdentifier + "\" OR " +
                Const.USER_PHONE + "=\"" + userIdentifier + "\") AND " +
                Const.USER_PASSWORD + "=MD5(\"" + password + "\")";

        try {
            Statement stat = getDbConnection().createStatement();
            set = stat.executeQuery(query);
        } catch (SQLException e){
        } catch (ClassNotFoundException e){
        }

        return set;
    }

    public ResultSet getUser(User user) {
        ResultSet set = null;

        String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " +
                Const.USER_USERNAME + "=\"" + user.getUserName() + "\"" + " OR " +
                Const.USER_EMAIL + "=\"" + user.geteMail() + "\" OR " +
                Const.USER_PHONE + "=\"" + user.getPhoneNumber() + "\"";

        try {
            Statement stat = getDbConnection().createStatement();
            set = stat.executeQuery(query);
        } catch (SQLException e){
        } catch (ClassNotFoundException e){
        }

        return set;
    }

    public ResultSet getChair(int chairId) {
        ResultSet set = null;

        String query = "SELECT * FROM " + Const.CHAIRS_TABLE + " WHERE " + Const.CHAIR_ID + "=" + chairId;

        try {
            Statement stat = getDbConnection().createStatement();
            set = stat.executeQuery(query);
        } catch (SQLException e){
        } catch (ClassNotFoundException e){
        }

        return set;
    }

    public void addReservation(Reserve reserve) {
        String insert = "INSERT INTO " + Const.RESERVATION_TABLE + "(" + Const.RESERVATION_USER_ID + "," + Const.RESERVATION_CHAIR_ID +
                        "," + Const.RESERVATION_START_DATE + "," + Const.RESERVATION_END_DATE + ") VALUES(" + reserve.getUserID() + ","
                        + reserve.getChairID() + ", ?, ?)";

        try {
            PreparedStatement stat = getDbConnection().prepareStatement(insert);
            stat.setTimestamp(1, Timestamp.valueOf(reserve.getStartDate()));
            stat.setTimestamp(2, Timestamp.valueOf(reserve.getEndDate()));

            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUserReservation(int userid) {
        ResultSet set = null;

        String query = "SELECT * FROM " + Const.RESERVATION_TABLE + " WHERE " + Const.RESERVATION_USER_ID + "=" + userid;

        try {
            Statement stat = getDbConnection().createStatement();
            set = stat.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return set;
    }

    public ResultSet getAllCurrentReservations() {
        ResultSet set = null;

        String query = "SELECT * FROM " + Const.RESERVATION_TABLE + " WHERE " +
                Const.RESERVATION_START_DATE + "<= NOW() AND " + Const.RESERVATION_END_DATE + "> NOW()";
        try {
            Statement stat = getDbConnection().createStatement();
            set = stat.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return set;
    }
}
