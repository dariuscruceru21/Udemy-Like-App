package Repository;

import Models.Course;
import Models.Instructor;
import Models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing Student entities in the database.
 */
public class StudentRepository extends DataBaseRepository<Student> {

    @Override
    public void create(Student student) {
        // Insert into User table with a manually specified user_id
        String sql = "INSERT INTO \"User\" (user_id, user_name, password, email, type) VALUES(?, ?, ?, ?, 'student')";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Manually specify the user_id (ensure it's unique)
            statement.setInt(1, student.getId());  // Use the user_id from the student
            statement.setString(2, student.getUserName());
            statement.setString(3, student.getPassword());
            statement.setString(4, student.getEmail());

            // Execute the insertion
            statement.executeUpdate();

            // Now insert into the student table with the same user_id
            String studentSQL = "INSERT INTO student (user_id) VALUES (?)";
            try (PreparedStatement statement1 = conn.prepareStatement(studentSQL)) {
                statement1.setInt(1, student.getId());  // Use the same user_id
                statement1.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Student get(Integer id) {
        String sql = "SELECT * FROM \"User\" WHERE user_id = ?";
        Student student = null;

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
                 statement.setInt(1, id);
                 ResultSet rs = statement.executeQuery();

                 if(rs.next()) {
                     student = new Student(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getString("email"), rs.getString("type"));
                     //load the students courses
                     student.setCourses(getCoursesForStudent(id));
                 }


        }catch (SQLException e) {
                 e.printStackTrace();
        }
        return student;

    }


    public List<Course> getCoursesForStudent(Integer studentId) {
        List<Course> courses = new ArrayList<>();

        // Query to check if the student is enrolled in any courses
        String checkCoursesSQL = "SELECT COUNT(*) FROM studentcourse WHERE student_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement checkStatement = conn.prepareStatement(checkCoursesSQL)) {

            checkStatement.setInt(1, studentId);
            ResultSet rs = checkStatement.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // If student is enrolled in courses, fetch the courses
                String sql = "SELECT c.course_id, c.course_title, c.description, c.available_spots, c.start_date, c.end_date, c.instructor_id " +
                        "FROM Course c " +
                        "JOIN studentcourse sc ON c.course_id = sc.course_id " +
                        "WHERE sc.student_id = ?";

                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, studentId);
                    ResultSet courseResultSet = statement.executeQuery();

                    while (courseResultSet.next()) {
                        Course course = new Course(
                                courseResultSet.getInt("course_id"),
                                courseResultSet.getString("course_title"),
                                courseResultSet.getString("description"),
                                courseResultSet.getInt("available_spots"),
                                courseResultSet.getString("start_date"),
                                courseResultSet.getString("end_date"),
                                getInstructorForCourse(courseResultSet.getInt("instructor_id"))
                        );
                        courses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }



    private Instructor getInstructorForCourse(Integer instructorId) throws SQLException {
        String query = "SELECT id, user_name, password, email, type FROM \"User\" WHERE user_id = ? AND type = 'instructor'";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, instructorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Instructor(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("type")
                );
            }
        }
        return null;
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE \"User\" SET user_name = ?, email = ? WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, student.getUserName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, student.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String deleteStudentSQL = "DELETE FROM student WHERE user_id = ?";
        String deleteUserSQL = "DELETE FROM \"User\" WHERE user_id = ?";

        try (Connection conn = getConnection()) {
            // First, delete the student record
            try (PreparedStatement statement = conn.prepareStatement(deleteStudentSQL)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

            // Then, delete the user record
            try (PreparedStatement statement = conn.prepareStatement(deleteUserSQL)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM \"User\" WHERE type = 'student'";
        List<Student> students = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getString("email"), rs.getString("type")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}

