package Models;

public class Admin extends User{

    public Admin(int userID, String userName, String password, String email) {
        super(userID, userName, password, email);
    }


    @Override
    public String toString() {
        return "\n" +
                "adminID = " + getId() + '\n' +
                "userName = " + getUserName() + '\n' +
                "email = " + getEmail();
    }
}
