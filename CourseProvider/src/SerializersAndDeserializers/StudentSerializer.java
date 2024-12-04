package SerializersAndDeserializers;

import Models.*;
import Models.Integer;
import Repository.FileRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * Serializer and deserializer for the {@link Student} class.
 * Converts {@link Student} objects to and from their string representation for file storage.
 */
public class StudentSerializer implements IEntitySerializer<Student> {
    CourseSerializer courseSerializer = new CourseSerializer();
    FileRepository<Course> courseFileRepository = new FileRepository<>("courses.csv",courseSerializer);
    InstructorSerializer instructorSerializer = new InstructorSerializer();
    FileRepository<Integer> instructorFileRepository = new FileRepository<>("instructor.csv",instructorSerializer);


    /**
     * Serializes an {@link Student} object into a string representation.
     *
     * @param student the {@link Student} object to be serialized.
     * @return a comma-separated string representing the {@link Student} object.
     */
    @Override
    public String serialize(Student student) {


        // Serialize the enrolled students list
        StringBuilder courseData = new StringBuilder();
        for (Course course : student.getCourses()) {
            courseData.append(course.getId()).append(",")
                    .append(course.getCourseTitle()).append(",")
                    .append(course.getDescription()).append(",").append(course.getAvailableSpots()).append(",").append(course.getStartDate()).append(",").append(course.getEndDate()).append(";");
        }
        return student.getId() + "," + student.getUserName() + ","+ student.getPassword()+ "," + student.getEmail()+ "," + student.getType() + "," + '[' + courseData + ']';


    }

    /**
     * Deserializes a string representation into an {@link Student} object.
     *
     * @param data the comma-separated string containing {@link Student} details.
     * @return an {@link Student} object created from the provided data.
     */
    @Override
    public Student deserialize(String data) {
        String[] parts = data.split(",");
        int id = java.lang.Integer.parseInt(parts[0]);
        String name = parts[1];
        String password = parts[2];
        String email = parts[3];
        String type = parts[4];
        Student s1 = new Student(id,name,password,email,type);
        int index = 5;
        while(index < parts.length && !parts[index].contains("]"))
            index++;

        StringBuilder coursesDataBuilder = new StringBuilder();
        for (int i = 5; i <= index; i++) {
            coursesDataBuilder.append(parts[i]).append(",");
        }

        // Remove the trailing comma if it exists
        String coursesData = coursesDataBuilder.toString();
        if (coursesData.endsWith(",")) {
            coursesData = coursesData.substring(0, coursesData.length() - 1); // Remove trailing comma
        }

        List<Course> courses = new ArrayList<>();
        if(!coursesData.isEmpty()){
            //remove the square brackets
            if(coursesData.startsWith("[") && coursesData.endsWith("]")){
                coursesData = coursesData.substring(1,coursesData.length() - 1);//remove the courses
            }

            //split the courses data by semicolon to get wac courses record
            String[] coursesEntries = coursesData.split(";");
            for(String coursesStr : coursesEntries){
                //now split eac course entry by comma
                String[] coursesParts = coursesStr.split(",");
                if(coursesParts.length == 6) {
                    java.lang.Integer courseId = java.lang.Integer.parseInt(coursesParts[0]);
                    String title = coursesParts[1];
                    String description = coursesParts[2];
                    java.lang.Integer availableSpots = java.lang.Integer.parseInt(coursesParts[3]);
                    String startDate = coursesParts[4];
                    String endDate = coursesParts[5];
                    courses.add(new Course(courseId,title,description,availableSpots,startDate,endDate,courseFileRepository.get(courseId).getInstructor()));
                }
            }
        }

        s1.setCourses(courses);
        return s1;

    }

}

