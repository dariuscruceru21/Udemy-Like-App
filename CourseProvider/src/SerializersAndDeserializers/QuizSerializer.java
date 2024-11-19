package SerializersAndDeserializers;

import Models.Admin;
import Models.IEntitySerializer;
import Models.Quiz;

public class QuizSerializer implements IEntitySerializer<Quiz> {
    @Override
    public String serialize(Quiz quiz) {
        return quiz.getId() + "," + quiz.getTitle() + "," + quiz.getContents() + "," + quiz.getAnswer();
    }

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
