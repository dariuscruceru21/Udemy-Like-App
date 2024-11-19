package SerializersAndDeserializers;

import Models.*;
import Repository.FileRepository;

import java.util.ArrayList;
import java.util.List;


public class InstructorSerializer implements IEntitySerializer<Instructor> {
    CourseSerializer courseSerializer = new CourseSerializer();
    FileRepository<Course> courseFileRepository = new FileRepository<>("courses.csv",courseSerializer);



    @Override
    public String serialize(Instructor instructor) {


        // Serialize the enrolled students list
        StringBuilder courseData = new StringBuilder();
        for (Course course : instructor.getCourses()) {
            courseData.append(course.getId()).append(",")
                    .append(course.getCourseTitle()).append(",")
                    .append(course.getDescription()).append(",").append(course.getAvailableSpots()).append(",").append(course.getStartDate()).append(",").append(course.getEndDate());
        }
        return instructor.getId() + "," + instructor.getUserName() + ","+ instructor.getPassword()+ "," + instructor.getEmail()+ "," + instructor.getType() + "," + courseData;


    }

    @Override
    public Instructor deserialize(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String password = parts[2];
        String email = parts[3];
        String type = parts[4];
        Integer courseId = Integer.parseInt(parts[5]);
        Instructor i1 =new Instructor(id,name,password,email,type);
        Course c1 = courseFileRepository.get(courseId);
        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        i1.setCourses(courses);
        return i1;
    }

}

