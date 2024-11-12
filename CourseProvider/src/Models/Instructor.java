package Models;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private List<Course> courses = new ArrayList<>();

    public Instructor(int userID, String userName, String password, String email) {
        super(userID, userName, password, email);
    }



    public List<Course> getCourses() {
        return this.courses;
    }

    @Override
    public String toString() {
        return "\n" +
                "userID = " + getId() + "\n" +
                "userName = " + getUserName() + "\n" +
                "email = " + getEmail() + "\n" +
                "courses = " + courses.toString();
    }

}
