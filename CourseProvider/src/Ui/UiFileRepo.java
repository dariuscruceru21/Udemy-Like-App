package Ui;

import Controller.ControllerCoursesUser;
import Models.*;
import Models.Module;
import Repository.FileRepository;
import Repository.IRepository;
import SerializersAndDeserializers.AdminSerializer;
import SerializersAndDeserializers.CourseSerializer;
import SerializersAndDeserializers.InstructorSerializer;
import SerializersAndDeserializers.StudentSerializer;

import java.util.Scanner;

public class UiFileRepo {
    CourseSerializer courseSerializer = new CourseSerializer();
    private  IRepository<Course> courses ;

    StudentSerializer studentSerializer = new StudentSerializer();
    private  IRepository<Student> students ;

    InstructorSerializer instructorSerializer = new InstructorSerializer();
    private  IRepository<Instructor> instructors ;

    AdminSerializer adminSerializer = new AdminSerializer();
    private  IRepository<Admin> admins;

    private final ControllerCoursesUser coursesUserController;
    private final Scanner scanner;


    public UiFileRepo(ControllerCoursesUser coursesUserController, Scanner scanner) {
        courses = new FileRepository<>("courses.csv",courseSerializer);
        IRepository<Student> students = new FileRepository<>("students.csv",studentSerializer);
        instructors = new FileRepository<>("instructors.csv",instructorSerializer);
        admins = new FileRepository<>("admin.csv",adminSerializer);
        this.coursesUserController = coursesUserController;
        this.scanner = scanner;
    }


    void showMenu(){

    }


}
