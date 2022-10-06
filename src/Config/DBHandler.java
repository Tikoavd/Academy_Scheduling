package Config;

import Users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void signUpNewUser(User user, String password) {
        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" +
                Const.USER_USERNAME + "," + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME +
                "," + Const.USER_EMAIL + "," + Const.USER_PHONE + "," + Const.USER_PASSWORD + ")" +
                "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prepStat = getDbConnection().prepareStatement(insert);
            prepStat.setString(1, user.getUserName());
            prepStat.setString(2, user.getFirstName());
            prepStat.setString(3, user.getLastName());
            prepStat.setString(4, user.geteMail());
            prepStat.setString(5, user.getPhoneNumber());
            prepStat.setString(6, password);

            prepStat.executeUpdate();
        } catch (SQLException e) {
        } catch (ClassNotFoundException e){
        }
    }
}
