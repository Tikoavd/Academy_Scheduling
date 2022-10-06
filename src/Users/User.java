package Users;

public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String eMail;
    private String phoneNumber;

    public User(String userName, String firstName, String lastName, String eMail, String phoneNumber){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }

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
}
