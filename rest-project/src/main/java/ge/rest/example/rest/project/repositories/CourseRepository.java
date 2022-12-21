/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.repositories;

import ge.rest.example.rest.project.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vako
 */
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByName(String name);
}
