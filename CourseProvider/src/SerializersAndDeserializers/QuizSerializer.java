package SerializersAndDeserializers;

import Models.IEntitySerializer;
import Models.Integer;
import Models.Quiz;

/**
 * Serializer and deserializer for the {@link Quiz} class.
 * Converts {@link Quiz} objects to and from their string representation for file storage.
 */
public class QuizSerializer implements IEntitySerializer<Quiz> {

    /**
     * Serializes an {@link Quiz} object into a string representation.
     *
     * @param quiz the {@link Quiz} object to be serialized.
     * @return a comma-separated string representing the {@link Quiz} object.
     */
    @Override
    public String serialize(Quiz quiz) {
        return quiz.getId() + "," + quiz.getTitle() + "," + quiz.getContents() + "," + quiz.getAnswer();
    }

    /**
     * Deserializes a string representation into an {@link Quiz} object.
     *
     * @param data the comma-separated string containing {@link Quiz} details.
     * @return an {@link Quiz} object created from the provided data.
     */
    @Override
    public Quiz deserialize(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        String title = parts[1];
        String content = parts[2];
        int answer = Integer.parseInt(parts[3]);
        return new Quiz(id,title,content,answer);
    }
}
