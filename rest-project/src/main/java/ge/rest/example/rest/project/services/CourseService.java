/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.ReturnTypeDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface CourseService {

    List<CourseDTO> getAllCourses();
    //

    CourseDTO getCourseById(Long id);

    CourseDTO createNewCourse(CourseDTO courseDTO);

    CourseDTO saveCourseByDTO(Long id, CourseDTO courseDTO);

    ReturnTypeDTO assignTeamTocourse(Long teamId, Long courseId);

    ReturnTypeDTO getActive(Long newid);

    List<ReturnTypeDTO> getAllActive();

    ReturnTypeDTO updateActive(Long newid, ReturnTypeDTO returnTypeDTO);

    void deleteCourseById(Long id);
    
    void DeleteActiveCourseById(Long newid);

}
