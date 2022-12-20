/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.bootstrap;

import ge.rest.example.rest.project.domain.Course;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.repositories.CourseRepository;
import ge.rest.example.rest.project.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author vako
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public Bootstrap(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCourse();
        loadStudents();
    }
    
     private void loadCourse() {
         Course biology = new Course();
         biology.setDescription("Biology");
         
         
          Course math = new Course();
         math.setDescription("Math");
     
         
          Course physics = new Course();
         physics.setDescription("Physics");
        
         
         courseRepository.save(biology);
         courseRepository.save(math);
         courseRepository.save(physics);
         
         System.out.println("Courses Loaded: " + courseRepository.count());
     }
       private void loadStudents() {
           Student student1 = new Student();
           student1.setFirstname("name1");
           student1.setLastname("lastname1");
           
           studentRepository.save(student1);
           System.out.println("Students Loaded: " + studentRepository.count());
       }
}
