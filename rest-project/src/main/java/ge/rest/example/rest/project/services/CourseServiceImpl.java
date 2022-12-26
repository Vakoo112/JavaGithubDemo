/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.controllers.CourseController;
import ge.rest.example.rest.project.domain.Course;
import ge.rest.example.rest.project.domain.Returntype;
import ge.rest.example.rest.project.domain.Team;
import ge.rest.example.rest.project.mapper.CourseMapper;
import ge.rest.example.rest.project.mapper.TeamMapper;
import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.ReturnTypeDTO;
import ge.rest.example.rest.project.repositories.CourseRepository;
import ge.rest.example.rest.project.repositories.ReturnRepo;
import ge.rest.example.rest.project.repositories.TeamRepository;
import java.util.List;
import java.util.Optional;
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
    private final TeamMapper teamMapper;
    private final ReturnRepo returnRepo;

    public CourseServiceImpl(CourseMapper courseMapper, CourseRepository courseRepository,
            TeamRepository teamRepository, TeamMapper teamMapper, ReturnRepo returnRepo) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.returnRepo = returnRepo;
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
    public ReturnTypeDTO getActive(Long newid) {
        Optional<Returntype> optional1 = returnRepo.findById(newid);
        if (!optional1.isPresent()) {
            throw new ResourceNotFoundException();
        }
        Returntype saveReturn = optional1.get();
        ReturnTypeDTO Change = courseMapper.change1(saveReturn);
        returnRepo.save(saveReturn);
        return Change;
    }

    @Override
    public List<ReturnTypeDTO> getAllActive() {
        return returnRepo.findAll()
                .stream()
                .map(course -> {
                    ReturnTypeDTO courseDTO = courseMapper.change1(course);
                    return courseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO createNewCourse(CourseDTO courseDTO) {

        return saveAndReturnDTO(courseMapper.courseDtoTocourse(courseDTO));
    }

    @Override
    public CourseDTO saveCourseByDTO(Long id, CourseDTO courseDTO) {
        return saveAndReturnDTO(courseMapper.courseDtoTocourse(courseDTO));
    }

    @Override
    public ReturnTypeDTO updateActive(Long newid, ReturnTypeDTO returnTypeDTO) {

        return saveAdnRetuntype(courseMapper.dtoToType(returnTypeDTO));
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

    private ReturnTypeDTO saveAdnRetuntype(Returntype returnType) {
        Returntype saveType = returnRepo.save(returnType);
        ReturnTypeDTO returnDTO = courseMapper.change1(saveType);
        return returnDTO;

    }

    @Override
    public ReturnTypeDTO assignTeamTocourse(Long teamId, Long courseId) {
        Optional<Team> teamopT = teamRepository.findById(teamId);
        if (!teamopT.isPresent()) {
            throw new ResourceNotFoundException();
        }
        Team team = teamopT.get();
        teamRepository.save(team);

        Optional<Course> courseopT = courseRepository.findById(courseId);
        if (!courseopT.isPresent()) {
            throw new ResourceNotFoundException();
        }
        Course course = courseopT.get();
        courseRepository.save(course);

        ReturnTypeDTO alltoa11 = courseMapper.alltoall(team, course);
        Returntype change1 = courseMapper.dtoToType(alltoa11);
        returnRepo.save(change1);

        return alltoa11;

    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void DeleteActiveCourseById(Long newid) {
        returnRepo.deleteById(newid);

    }
}
