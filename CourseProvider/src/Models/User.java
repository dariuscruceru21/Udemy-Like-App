package Models;

public abstract class User implements Identifiable {
    private int userID;
    private String userName;
    private String password;
    private String email;

    User(int userID, String userName, String password, String email) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }


    @Override
    public int getId() {
        return this.userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}
