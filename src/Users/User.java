package Users;

public class User {
    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String eMail;
    private String phoneNumber;
    private String password;

    public User(String userName, String firstName, String lastName, String eMail, String phoneNumber, String password){
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        seteMail(eMail);
        setPhoneNumber(phoneNumber);
        setPassword(password);
    }

    public int getUserID() { return userID; }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(int userID) { this.userID = userID; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail.toLowerCase();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
