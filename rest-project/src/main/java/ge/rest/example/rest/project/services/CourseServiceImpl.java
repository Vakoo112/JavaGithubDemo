/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.domain.Course;
import ge.rest.example.rest.project.mapper.CourseMapper;
import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.CourseResponseDTO;
import ge.rest.example.rest.project.repositories.CourseRepository;
import ge.rest.example.rest.project.repositories.TeamRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vako
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseMapper courseMapper, CourseRepository courseRepository,
            TeamRepository teamRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(course -> {
                    CourseResponseDTO courseResponseDTO = courseMapper.courseToresponse(course);
                    courseResponseDTO.setCourseId(course.getId());
                    return courseResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {

        Optional<Course> courseOPT = courseRepository.findById(id);
        Course course = courseOPT.get();
        CourseResponseDTO courseResponse = new CourseResponseDTO();
        courseResponse = courseMapper.courseToresponse(course);
        courseResponse.setCourseId(course.getId());
        return courseResponse;
    }

    @Override
    @Transactional
    public CourseResponseDTO createNewCourse(CourseDTO courseDTO) {
        Optional<Course> courseName = courseRepository.findByName(courseDTO.getName());
        if(courseName.isPresent()) {
         throw new RuntimeException("course name already exists");   
        } else {
        Course course = courseMapper.courseDtoTocourse(courseDTO);
        courseRepository.save(course);

        CourseResponseDTO courseResponse = courseMapper.courseToresponse(course);
        courseResponse.setCourseId(course.getId());
        return courseResponse;
        }
    }

    @Override
    @Transactional
    public CourseResponseDTO updateCourseByDTO(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).get();
        course.setDescription(courseDTO.getDescription());
        course.setName(courseDTO.getName());
        CourseResponseDTO courseResponse = new CourseResponseDTO();
        courseResponse.setCourseId(course.getId());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setName(course.getName());
        return courseResponse;
    }

    @Override
    @Transactional
    public void deleteCourseById(Long id) {
        Course course = courseRepository.findById(id).get();
        if (course.getTeams().isEmpty()) {
            courseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Course is used");
        }
    }
}
