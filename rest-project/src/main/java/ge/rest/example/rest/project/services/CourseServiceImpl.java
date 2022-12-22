/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.controllers.CourseController;
import ge.rest.example.rest.project.domain.Course;
import ge.rest.example.rest.project.mapper.CourseMapper;
import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.repositories.CourseRepository;
import ge.rest.example.rest.project.repositories.TeamRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author vako
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final TeamRepository teamRepository;

    public CourseServiceImpl(CourseMapper courseMapper, CourseRepository courseRepository, TeamRepository teamRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
        this.teamRepository = teamRepository;

    }

    @Override
    public List<CourseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(course -> {
                    CourseDTO courseDTO = courseMapper.courseToCourseDTO(course);
                    courseDTO.setCourseUrl(getCourseUrl(course.getId()));
                    return courseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {

        return courseRepository.findById(id)
                .map(courseMapper::courseToCourseDTO)
                .map(courseDTO -> {
                    courseDTO.setCourseUrl(getCourseUrl(id));
                    return courseDTO;
                })
                .orElseThrow(RuntimeException::new);

    }

    @Override
    public CourseDTO createNewCourse(CourseDTO courseDTO) {

        return saveAndReturnDTO(courseMapper.courseDtoTocourse(courseDTO));
    }

    @Override
    public CourseDTO saveCourseByDTO(Long id, CourseDTO courseDTO) {
        return saveAndReturnDTO(courseMapper.courseDtoTocourse(courseDTO));
    }

    private CourseDTO saveAndReturnDTO(Course course) {
        Course saveCourse = courseRepository.save(course);
        CourseDTO returnDto = courseMapper.courseToCourseDTO(saveCourse);

        returnDto.setCourseUrl(getCourseUrl(saveCourse.getId()));
        return returnDto;
    }

    private String getCourseUrl(Long id) {
        return CourseController.BASE_URL + "/" + id;
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

}
