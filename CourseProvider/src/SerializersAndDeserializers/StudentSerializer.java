package SerializersAndDeserializers;

import Models.*;
import Repository.FileRepository;

import java.util.ArrayList;
import java.util.List;


public class StudentSerializer implements IEntitySerializer<Student> {
    CourseSerializer courseSerializer = new CourseSerializer();
    FileRepository<Course> courseFileRepository = new FileRepository<>("courses.csv",courseSerializer);
    InstructorSerializer instructorSerializer = new InstructorSerializer();
    FileRepository<Instructor> instructorFileRepository = new FileRepository<>("instructor.csv",instructorSerializer);



    @Override
    public String serialize(Student student) {


        // Serialize the enrolled students list
        StringBuilder courseData = new StringBuilder();
        for (Course course : student.getCourses()) {
            courseData.append(course.getId()).append(",")
                    .append(course.getCourseTitle()).append(",")
                    .append(course.getDescription()).append(",").append(course.getAvailableSpots()).append(",").append(course.getStartDate()).append(",").append(course.getEndDate());
        }
        return student.getId() + "," + student.getUserName() + ","+ student.getPassword()+ "," + student.getEmail()+ "," + student.getType() + "," + courseData;


    }

    @Override
    public Student deserialize(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String password = parts[2];
        String email = parts[3];
        String type = parts[4];
        Integer courseId = Integer.parseInt(parts[5]);
        Student s1 = new Student(id,name,password,email,type);
        Course c1 = courseFileRepository.get(courseId);
        s1.addToCourseList(c1);
        return s1;

    }

}

