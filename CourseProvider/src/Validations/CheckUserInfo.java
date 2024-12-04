package Validations;

import Models.Course;
import Models.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CheckUserInfo {

    /**
     * Validates if an email has the correct format.
     * @param email The email to validate.
     * @return True if the email format is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    /**
     * Validates if the input is a valid integer.
     * @param input The input string to validate.
     * @return True if the input can be parsed as an integer, false otherwise.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if the input is a valid string.
     * @param input The input to validate.
     * @return True if the input is a non-empty string, false otherwise.
     */
    public static boolean isString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Validates if a message is shorter than 100 characters.
     * @param message The message to validate.
     * @return True if the message is less than 100 characters, false otherwise.
     */
    public static boolean isMessageUnder100Chars(String message) {
        return message != null && message.length() <= 100;
    }

    /**
     * Validates if a name contains only alphabetic characters and spaces.
     * @param name The name to validate.
     * @return True if the name is valid, false otherwise.
     */
    public static boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z ]+");
    }

    /**
     * Validates if a string contains no special characters.
     * @param input The string to validate.
     * @return True if the string contains only letters and numbers, false otherwise.
     */
    public static boolean containsNoSpecialChars(String input) {
        return input != null && input.matches("[A-Za-z0-9]+");
    }

    /**
     * Validates if a number is a positive integer.
     * @param number The number to validate.
     * @return True if the number is positive, false otherwise.
     */
    public static boolean isPositiveInteger(int number) {
        return number > 0;
    }

    /**
     * Validates if a number is within a specified range.
     * @param number The number to validate.
     * @param min The minimum allowable value.
     * @param max The maximum allowable value.
     * @return True if the number is within range, false otherwise.
     */
    public static boolean isWithinRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    /**
     * Validates if a date string follows the "yyyy-MM-dd" format.
     * @param date The date string to validate.
     * @return True if the date format is valid, false otherwise.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validates if a given date is in the future.
     * @param date The date to validate.
     * @return True if the date is in the future, false otherwise.
     */
    public static boolean isFutureDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    /**
     * Validates if a username is unique by checking against a list of existing usernames.
     * @param username The username to validate.
     * @param existingUsernames A list of existing usernames.
     * @return True if the username is unique, false otherwise.
     */
    public static boolean isUniqueUsername(String username, List<String> existingUsernames) {
        return username != null && !existingUsernames.contains(username);
    }

    /**
     * Validates if a password meets strength requirements.
     * @param password The password to validate.
     * @return True if the password is strong, false otherwise.
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
    }

    /**
     * Validates if a course has available spots for new enrollments.
     * @param course The course to validate.
     * @return True if the course has available spots, false otherwise.
     */
    public static boolean hasAvailableSpots(Course course) {
        return course.getEnrolledStudents().size() < course.getAvailableSpots();
    }

    /**
     * Validates if a course's end date is after its start date.
     * @param startDate The start date of the course.
     * @param endDate The end date of the course.
     * @return True if the end date is after the start date, false otherwise.
     */
    public static boolean isEndDateAfterStartDate(LocalDate startDate, LocalDate endDate) {
        return endDate != null && startDate != null && endDate.isAfter(startDate);
    }

    /**
     * Validates if a user's role is allowed for a specific action.
     * @param role The role to validate.
     * @param allowedRoles A list of roles allowed for the action.
     * @return True if the role is allowed, false otherwise.
     */
    public static boolean isValidRole(String role, List<String> allowedRoles) {
        return allowedRoles.contains(role);
    }

    /**
     * Validates if an ID is unique in a list of existing IDs.
     * @param id The ID to validate.
     * @param existingIds A list of existing IDs.
     * @return True if the ID is unique, false otherwise.
     */
    public static boolean isUniqueId(int id, List<Integer> existingIds) {
        return !existingIds.contains(id);
    }

    /**
     * Validates if a student has completed all prerequisite courses.
     * @param student The student to validate.
     * @param prerequisites A list of prerequisite courses.
     * @return True if the student has completed all prerequisites, false otherwise.
     */
    public static boolean hasCompletedPrerequisites(Student student, List<Course> prerequisites) {
        return prerequisites.stream().allMatch(student::hasCompletedCourse);
    }

    /**
     * Validates if a student is already enrolled in a course.
     * @param student The student to validate.
     * @param course The course to check enrollment in.
     * @return True if the student is already enrolled, false otherwise.
     */
    public static boolean isAlreadyEnrolled(Student student, Course course) {
        return course.getEnrolledStudents().contains(student);
    }

    /**
     * Validates if a string does not exceed a maximum length.
     * @param input The string to validate.
     * @param maxLength The maximum allowable length.
     * @return True if the string is within the allowed length, false otherwise.
     */
    public static boolean isValidLength(String input, int maxLength) {
        return input != null && input.length() <= maxLength;
    }
}
