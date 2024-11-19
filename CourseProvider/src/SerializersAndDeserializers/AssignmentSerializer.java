package SerializersAndDeserializers;

import Models.Assignment;
import Models.IEntitySerializer;
import Models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class AssignmentSerializer implements IEntitySerializer<Assignment> {
    @Override
    public String serialize(Assignment assignment) {
        // Serialize the quiz list
        StringBuilder assignmentData = new StringBuilder();
        for (Models.Quiz quiz : assignment.getQuizzes()) {
            assignmentData.append(quiz.getId()).append(",")
                    .append(quiz.getTitle()).append(",")
                    .append(quiz.getContents()).append(",")
                    .append(quiz.getAnswer()).append(";");
        }

        return assignment.getId() + "," +
                assignment.getDescription() + "," +
                assignment.getDueDate() + "," + "[" +
                assignmentData + ']' + "," +
                assignment.getScore();
    }

    @Override
    public Assignment deserialize(String data) {
        // Split the data by commas
        String[] parts = data.split(",", -1); // Use -1 to preserve empty parts (e.g., empty lists)

        // Ensure we have enough parts
        if (parts.length < 5) {
            throw new IllegalArgumentException("Invalid assignment data format, not enough fields");
        }

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
                    int quizAnswer = Integer.parseInt(parts[3]);
                    quizzes.add(new Quiz(quizId, quizTitle, quizContents, quizAnswer));
                } else {
                    throw new IllegalArgumentException("Quiz data format is invalid. Expected 4 fields, found: " + quizParts.length);
                }
            }
        }

        int score = Integer.parseInt(parts[4]);

        // Create and return the Assignment object
        Assignment assignment = new Assignment(assignmentId, description, dueDate, score);

        assignment.setQuizzes(quizzes);

        return assignment;
    }
}
