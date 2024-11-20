package SerializersAndDeserializers;

import Models.Assignment;
import Models.IEntitySerializer;
import Models.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Serializer and deserializer for the {@link Assignment} class.
 * Converts {@link Assignment} objects to and from their string representation for file storage.
 * The string representation includes the assignment's ID, description, due date, quizzes, and score.
 */
public class AssignmentSerializer implements IEntitySerializer<Assignment> {

    /**
     * Serializes an {@link Assignment} object into a string representation.
     *
     * The format of the serialized data is:
     * <pre>
     * ID,description,dueDate,[quiz1Data;quiz2Data;...],score
     * </pre>
     * where each quiz data is represented as:
     * <pre>
     * quizId,quizTitle,quizContents,quizAnswer;
     * </pre>
     *
     * @param assignment the {@link Assignment} object to serialize.
     * @return a string representation of the {@link Assignment}.
     */
    @Override
    public String serialize(Assignment assignment) {
        // Serialize the list of quizzes
        StringBuilder assignmentData = new StringBuilder();
        for (Quiz quiz : assignment.getQuizzes()) {
            assignmentData.append(quiz.getId()).append(",")
                    .append(quiz.getTitle()).append(",")
                    .append(quiz.getContents()).append(",")
                    .append(quiz.getAnswer()).append(";");
        }

        return assignment.getId() + "," +
                assignment.getDescription() + "," +
                assignment.getDueDate() + "," +
                "[" + assignmentData + "]" + "," +
                assignment.getScore();
    }

    /**
     * Deserializes a string representation into an {@link Assignment} object.
     *
     * The input string should follow the format:
     * <pre>
     * ID,description,dueDate,[quiz1Data;quiz2Data;...],score
     * </pre>
     * where each quiz data is represented as:
     * <pre>
     * quizId,quizTitle,quizContents,quizAnswer;
     * </pre>
     *
     * @param data the string representation of an {@link Assignment}.
     * @return an {@link Assignment} object created from the provided data.
     * @throws IllegalArgumentException if the quiz data is not properly formatted.
     * @throws NumberFormatException if any numeric value cannot be parsed.
     */
    @Override
    public Assignment deserialize(String data) {
        // Split the serialized data by commas
        String[] parts = data.split(",", -1); // Use -1 to retain empty parts, especially for lists

        // Extract assignment fields
        Integer assignmentId = Integer.parseInt(parts[0]);
        String description = parts[1];
        String dueDate = parts[2];

        // Extract and process the quizzes data
        String quizzesData = parts[3];
        if (quizzesData.startsWith("[") && quizzesData.endsWith("]")) {
            quizzesData = quizzesData.substring(1, quizzesData.length() - 1); // Remove square brackets
        }

        // Deserialize quizzes
        List<Quiz> quizzes = new ArrayList<>();
        if (!quizzesData.isEmpty()) {
            String[] quizEntries = quizzesData.split(";");
            for (String quizEntry : quizEntries) {
                String[] quizParts = quizEntry.split(",");
                if (quizParts.length == 4) {
                    Integer quizId = Integer.parseInt(quizParts[0]);
                    String quizTitle = quizParts[1];
                    String quizContents = quizParts[2];
                    int quizAnswer = Integer.parseInt(quizParts[3]);
                    quizzes.add(new Quiz(quizId, quizTitle, quizContents, quizAnswer));
                } else {
                    throw new IllegalArgumentException("Quiz data format is invalid. Expected 4 fields, found: " + quizParts.length);
                }
            }
        }

        int score = Integer.parseInt(parts[4]);

        // Create and return the Assignment object
        Assignment assignment = new Assignment(assignmentId, description, dueDate, score);

        // Set the quizzes list
        assignment.setQuizzes(quizzes);

        return assignment;
    }
}
