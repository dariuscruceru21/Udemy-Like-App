package Service;

// AuthenticationService.java
import Models.Admin;
import Models.Instructor;
import Models.Student;
import Models.User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

    private Map<String, User> usersDatabase;

    public AuthenticationService() {
        usersDatabase = new HashMap<>();
        // Initialize with some sample users (In real applications, this data should come from a database)
        usersDatabase.put("stefan", new Student(1, "stefan", "abc", "s.h@gmail.com", true));
        usersDatabase.put("darius", new Instructor(2, "darius", "abc", "d.c@gmail.com"));
        usersDatabase.put("robert", new Admin(3, "robert", "abc", "r.c@gmail.com"));
    }

    public User authenticate(String username, String password) {
        User user = usersDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;  // If authentication fails
    }
}

