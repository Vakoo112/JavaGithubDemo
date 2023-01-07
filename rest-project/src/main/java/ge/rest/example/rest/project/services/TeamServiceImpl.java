/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.domain.Course;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.domain.Team;
import ge.rest.example.rest.project.mapper.TeamMapper;
import ge.rest.example.rest.project.model.AssignDTO;
import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.TeamDTO;
import ge.rest.example.rest.project.model.TeamRespponseDTO;
import ge.rest.example.rest.project.repositories.CourseRepository;
import ge.rest.example.rest.project.repositories.StudentRepository;
import ge.rest.example.rest.project.repositories.TeamRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author vako
 */
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public TeamServiceImpl(TeamMapper teamMapper, TeamRepository teamRepository,
            CourseRepository courseRepository, CourseService courseService, StudentRepository studentRepository) {
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<TeamRespponseDTO> getAllTeam() {

        return teamRepository
                .findAll()
                .stream()
                .map(team -> {
                    TeamRespponseDTO teamRespponseDTO = teamMapper.teamToResponse(team);

                    return teamRespponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TeamRespponseDTO getTeamsById(Long id) {

        Optional<Team> teamOPT = teamRepository.findById(id);
        Team team = teamOPT.get();
        Course course = courseRepository.findById(team.getId()).get();
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        TeamRespponseDTO teamResponse = new TeamRespponseDTO();
        teamResponse.setTeamname(team.getTeamname());
        teamResponse.setStarttime(team.getStarttime());
        teamResponse.setEndtime(team.getEndtime());
        teamResponse.setMaxstudentsenrolled(team.getMaxstudentsenrolled());
        teamResponse.setCourse(courseDTO);

        return teamResponse;

    }

    //--
    @Override
    public AssignDTO assignStudentToTeam(Long teamId, Long studentId) {
        Set<Student> studentSet = null;
        Team team = teamRepository.findById(teamId).get();
        Student student = studentRepository.findById(studentId).get();
        studentSet = team.getStudents();
        studentSet.add(student);
        team.setStudents(studentSet);
        //teamRepository.save(team);
        //studentRepository.save(student);
        Set<StudentDTO> studentSetDTO = null;
        AssignDTO assign = new AssignDTO();
        assign.setCourseId(team.getCourse().getId());
        assign = teamMapper.teamtoAssign(team);
        teamRepository.save(team);
        studentRepository.save(student);
        return assign;

    }

    @Override
    public TeamRespponseDTO createNewTeam(TeamDTO teamDTO) {
        Course course = courseRepository.findById(teamDTO.getCourseId()).get();

        Team team = new Team();
        //

        team.setTeamname(teamDTO.getTeamname());
        team.setCourse(course);
        team.setMaxstudentsenrolled(teamDTO.getMaxstudentsenrolled());
        team.setEndtime(teamDTO.getEndtime());
        team.setStarttime(teamDTO.getStarttime());

        team = teamRepository.save(team);

        CourseDTO coursedto = new CourseDTO();
        coursedto.setName(course.getName());
        coursedto.setDescription(course.getDescription());

        TeamRespponseDTO team1 = new TeamRespponseDTO();
        team1.setEndtime(team.getEndtime());
        team1.setTeamname(team.getTeamname());
        team1.setStarttime(team.getStarttime());
        team1.setMaxstudentsenrolled(team.getMaxstudentsenrolled());
        team1.setCourse(coursedto);

        return team1;
    }

    @Override
    public TeamRespponseDTO updateTeam(Long id, TeamDTO teamDTO) {
        Course course = courseRepository.findById(teamDTO.getCourseId()).get();
        Optional<Team> teamOPT = teamRepository.findById(id);
        Team team = teamOPT.get();
        team.setTeamname(teamDTO.getTeamname());
        team.setEndtime(teamDTO.getEndtime());
        team.setStarttime(team.getStarttime());
        team.setMaxstudentsenrolled(teamDTO.getMaxstudentsenrolled());
        team.setCourse(course);

        team = teamRepository.save(team);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());

        TeamRespponseDTO teamResponse = new TeamRespponseDTO();
        teamResponse.setTeamname(team.getTeamname());
        teamResponse.setStarttime(team.getStarttime());
        teamResponse.setEndtime(team.getEndtime());
        teamResponse.setCourse(courseDTO);
        return teamResponse;
    }

    @Override
    public void deleteTeamById(Long id) {

        teamRepository.deleteById(id);

    }

}
