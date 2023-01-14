/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.CourseResponseDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface CourseService {

    List<CourseResponseDTO> getAllCourses();
    //

    CourseResponseDTO getCourseById(Long id);

    CourseResponseDTO createNewCourse(CourseDTO courseDTO);

    CourseResponseDTO updateCourseByDTO(Long id, CourseDTO courseDTO);

    void deleteCourseById(Long id);

}
