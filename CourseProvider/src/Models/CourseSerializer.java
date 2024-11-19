package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseSerializer implements IEntitySerializer<Course>{

    @Override
    public String serialize(Course course) {
        // Serialize the instructor completely (ID, username, password, email, type)
        String instructorData = (course.getInstructor() != null)
                ? course.getInstructor().getId() + ","
                + course.getInstructor().getUserName() + ","
                + course.getInstructor().getPassword() + ","
                + course.getInstructor().getEmail() + ","
                + course.getInstructor().getType()
                : "null";

        // Serialize the enrolled students list
        StringBuilder studentsData = new StringBuilder();
        for (Student student : course.getEnrolledStudents()) {
            studentsData.append(student.getId()).append(",")
                    .append(student.getUserName()).append(",")
                    .append(student.getPassword()).append(",").append(student.getEmail()).append(",").append(student.getType()).append(";");
        }

        // Serialize the modules list
        StringBuilder modulesData = new StringBuilder();
        for (Module module : course.getModules()) {
            modulesData.append(module.getId()).append(",")
                    .append(module.getModuleTitle()).append(",")
                    .append(module.getModuleContent()).append(";");
        }

        // Return the serialized data for the course
        return course.getId() + "," +
                course.getCourseTitle() + "," +
                course.getDescription() + "," +
                course.getAvailableSpots() + "," +
                course.getStartDate() + "," +
                course.getEndDate() + "," + "[" +
                instructorData + ']' + "," + '[' +
                studentsData.toString() + ']'+ "," + '[' +
                modulesData.toString() + ']';
    }

    @Override
    public Course deserialize(String data) {
        // Split the data by commas
        String[] parts = data.split(",", -1); // Use -1 to preserve empty parts (e.g., in case of empty lists)

        // Ensure we have enough parts
        if (parts.length < 20) {
            throw new IllegalArgumentException("Invalid course data format, not enough fields");
        }

        // Extract course fields
        Integer courseID = Integer.parseInt(parts[0]);
        String courseTitle = parts[1];
        String description = parts[2];
        Integer availableSpots = Integer.parseInt(parts[3]);
        String startDate = parts[4];
        String endDate = parts[5];

        // Concatenate parts from 6 to 11 to get the complete instructor data
        StringBuilder instructorDataBuilder = new StringBuilder();
        for (int i = 6; i <= 10; i++) {
            instructorDataBuilder.append(parts[i]).append(",");
        }

        // Remove the trailing comma if it exists
        String instructorData = instructorDataBuilder.toString();
        if (instructorData.endsWith(",")) {
            instructorData = instructorData.substring(0, instructorData.length() - 1); // Remove trailing comma
        }

        System.out.println("Instructor Data: " + instructorData);  // Debugging line to see the raw input

        // Check if instructor data is not "null"
        Instructor instructor = null;
        if (!instructorData.equals("null")) {
            // Trim the spaces and check for proper square brackets at the beginning and end
            instructorData = instructorData.trim();

            if (instructorData.startsWith("[") && instructorData.endsWith("]")) {
                // Remove the square brackets
                instructorData = instructorData.substring(1, instructorData.length() - 1); // Remove the brackets
            } else {
                // If the instructor data is not enclosed in square brackets, it's invalid
                throw new IllegalArgumentException("Instructor data is not in the correct format (missing square brackets). Raw input: " + instructorData);
            }

            // Split the instructor data by commas
            String[] instructorParts = instructorData.split(",");

            // Debugging: Print out the instructor parts to see the structure
            System.out.println("Instructor Parts: " + Arrays.toString(instructorParts));

            if (instructorParts.length == 5) {
                // Extract the instructor data correctly
                Integer instructorId = Integer.parseInt(instructorParts[0]); // Instructor ID
                String instructorUserName = instructorParts[1]; // Instructor Username
                String instructorPassword = instructorParts[2]; // Instructor Password
                String instructorEmail = instructorParts[3]; // Instructor Email
                String instructorType = instructorParts[4]; // Instructor Type
                instructor = new Instructor(instructorId, instructorUserName, instructorPassword, instructorEmail, instructorType);
            } else {
                // If there aren't exactly 5 parts, throw an exception
                throw new IllegalArgumentException("Instructor data format is invalid. Expected 5 fields, found: " + instructorParts.length);
            }
        }

        // Concatenate parts from 12 to 18 to get the complete students data
        StringBuilder studentsDataBuilder = new StringBuilder();
        for (int i = 11; i <= 19; i++) {
            studentsDataBuilder.append(parts[i]).append(",");
        }

        // Remove the trailing comma if it exists
        String studentsData = studentsDataBuilder.toString();
        if (studentsData.endsWith(",")) {
            studentsData = studentsData.substring(0, studentsData.length() - 1); // Remove trailing comma
        }

        System.out.println("Students Data: " + studentsData);  // Debugging line to see the raw input

        // Deserialize the students list
        List<Student> students = new ArrayList<>();
        if (!studentsData.isEmpty()) {
            // Remove the square brackets
            if (studentsData.startsWith("[") && studentsData.endsWith("]")) {
                studentsData = studentsData.substring(1, studentsData.length() - 1); // Remove the brackets
            }

            // Split the student data by semicolons to get each student's record
            String[] studentEntries = studentsData.split(";");
            for (String studentStr : studentEntries) {
                // Now split each student entry by commas to extract their individual fields
                String[] studentParts = studentStr.split(",");
                if (studentParts.length == 5) { // Ensure there are exactly 5 parts for a student
                    Integer studentId = Integer.parseInt(studentParts[0]);
                    String studentName = studentParts[1];
                    String studentPassword = studentParts[2];
                    String studentEmail = studentParts[3];
                    String studentType = studentParts[4];
                    students.add(new Student(studentId, studentName, studentPassword, studentEmail, studentType));
                } else {
                    throw new IllegalArgumentException("Student data format is invalid. Expected 5 fields, found: " + studentParts.length);
                }
            }
        }

        // Concatenate parts from 19 to the end to get the complete modules data
        StringBuilder modulesDataBuilder = new StringBuilder();
        for (int i = 20; i < 25; i++) {
            modulesDataBuilder.append(parts[i]).append(",");
        }

        // Remove the trailing comma if it exists
        String modulesData = modulesDataBuilder.toString();
        if (modulesData.endsWith(",")) {
            modulesData = modulesData.substring(0, modulesData.length() - 1); // Remove trailing comma
        }

        System.out.println("Modules Data: " + modulesData);  // Debugging line to see the raw input

        // Deserialize the modules list
        List<Module> modules = new ArrayList<>();
        if (!modulesData.isEmpty()) {
            // Remove the square brackets
            if (modulesData.startsWith("[") && modulesData.endsWith("]")) {
                modulesData = modulesData.substring(1, modulesData.length() - 1); // Remove the brackets
            }

            // Split the module data by semicolons to get each module's record
            String[] moduleEntries = modulesData.split(";");
            for (String moduleStr : moduleEntries) {
                // Now split each module entry by commas to extract their individual fields
                String[] moduleParts = moduleStr.split(",");
                if (moduleParts.length == 3) { // Ensure there are exactly 3 parts for a module
                    Integer moduleId = Integer.parseInt(moduleParts[0]);
                    String moduleTitle = moduleParts[1];
                    String moduleContent = moduleParts[2];
                    modules.add(new Module(moduleId, moduleTitle, moduleContent));
                } else {
                    throw new IllegalArgumentException("Module data format is invalid. Expected 3 fields, found: " + moduleParts.length);
                }
            }
        }

        // Create the Course object
        Course course = new Course(courseID, courseTitle, description, availableSpots, startDate, endDate, instructor);

        // Set the lists of students and modules
        course.setEnrolledStudents(students);
        course.setModules(modules);

        return course;
    }

}

