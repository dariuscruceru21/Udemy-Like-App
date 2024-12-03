package Repository;

import Models.Course;
import Models.Instructor;


import javax.xml.crypto.Data;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Repository for managing Instructor entities in the database.
 */
public class InstructorRepository extends DataBaseRepository<Instructor> {
    @Override
    public void create(Instructor instructor) {
        //insert into User table with a manually specified user_id
        String sql = "INSERT INTO \"User\" (user_id, user_name, password, email, type) VALUES(?, ?, ?, ?, 'instructor')";


        try(Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){

            //Manualy specify user_id
            statement.setInt(1,instructor.getId());
            statement.setString(2, instructor.getUserName());
            statement.setString(3, instructor.getPassword());
            statement.setString(4, instructor.getEmail());

            //execute the insertion
            statement.executeUpdate();

            //now insert into instructor table with the same user_id
            String instructorSQL = "INSERT INTO instructor (user_id) VALUES (?)";
            try (PreparedStatement statement1 = conn.prepareStatement(instructorSQL)){
                statement1.setInt(1,instructor.getId());
                statement1.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Instructor get(Integer id) {
        String sql = "SELECT * FROM \"User\" WHERE user_id = ?";
        Instructor instructor = null;

        try(Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                instructor = new Instructor(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getString("email"), rs.getString("type"));
                //load the instructors courses
                instructor.setCourses(getCoursesForInstructor(id));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return instructor;
    }

    public List<Course> getCoursesForInstructor(Integer instrucotId) {
        List<Course> courses = new ArrayList<>();

        // Query to check if the instructor is enrolled in any courses
        String checkCoursesSQL = "SELECT COUNT(*) FROM instructorcourse WHERE instructor_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement checkStatement = conn.prepareStatement(checkCoursesSQL)) {

            checkStatement.setInt(1, instrucotId);
            ResultSet rs = checkStatement.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // If instructor is enrolled in courses, fetch the courses
                String sql = "SELECT c.course_id, c.course_title, c.description, c.available_spots, c.start_date, c.end_date, c.instructor_id " +
                        "FROM Course c " +
                        "JOIN instructorcourse ic ON c.course_id = ic.course_id " +
                        "WHERE ic.instructor_id = ?";

                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, instrucotId);
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
    public void update(Instructor obj) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Instructor> getAll() {
        return List.of();
    }
}
