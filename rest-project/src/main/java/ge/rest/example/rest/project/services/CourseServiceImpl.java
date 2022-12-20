/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.mapper.CourseMapper;
import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.repositories.CourseRepository;
import ge.rest.example.rest.project.repositories.StudentRepository;
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
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseMapper courseMapper, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<CourseDTO> getAllCourses(){

        return courseRepository.findAll()
                .stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }
        @Override
    public CourseDTO getCourseByName(String description) {
        return courseMapper.courseToCourseDTO(courseRepository.findByName(description));
    }
}
