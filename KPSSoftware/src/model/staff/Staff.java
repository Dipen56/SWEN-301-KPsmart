package model.staff;

/**
 * User class of the KPS, which has a username,
 * password and a boolean to represent isManager status.
 *
 * @author Betty
 */
public class Staff {
    public final int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean isManager;

    /**
     * Constructor
     *
     * @param id
     * @param userName
     * @param password
     * @param isManager
     */
    public Staff(int id, String userName, String password, boolean isManager) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isManager = isManager;
    }

    /**
     * Constructor with more information
     *
     * @param id
     * @param userName
     * @param password
     * @param isManager
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     */
    public Staff(int id, String userName, String password, boolean isManager, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isManager = isManager;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Checks if the given username and password matches this Staff.
     *
     * @param username
     * @param password
     * @return true if both match, or false if not.
     */
    public boolean checkCredentials(String username, String password) {
        return this.userName.equals(username) && this.password.equals(password);
    }

    // USER ACCOUNT METHODS

    public String editPassword(String oldPassword, String newPassword, String retypePassword) {

        if (!oldPassword.equals(password)) {
            return "Old Passwords not match";
        } else if (!newPassword.equals(retypePassword)) {
            return "New passwords and re typed password do not match";
        } else if (newPassword.matches("[^a-zA-Z0-9]")) {
            // FIXME: 31/05/2017 the regex is not working :(((((((
            return "New passwords must only contain alphanumeric ";
        } else if (newPassword.length() < 6) {
            return "New passwords must be minimum 6 characters";
        } else {
            password = newPassword;
            return "Password change successful";
        }
    }

    /*
     * Getter and Setter methods.
     */

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String userName) {
        this.firstName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isManager() {
        return isManager;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isManager=" + isManager +
                '}';
    }
}
