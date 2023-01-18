/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.domain.Course;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.domain.Team;
import ge.rest.example.rest.project.mapper.CourseMapper;
import ge.rest.example.rest.project.mapper.TeamMapper;
import ge.rest.example.rest.project.model.At;
import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.TeamDTO;
import ge.rest.example.rest.project.model.TeamRespponseDTO;
import ge.rest.example.rest.project.repositories.CourseRepository;
import ge.rest.example.rest.project.repositories.StudentRepository;
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
public class TeamServiceImpl implements TeamService {

    private final CourseMapper courseMapper;
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public TeamServiceImpl(CourseMapper courseMapper, TeamMapper teamMapper, TeamRepository teamRepository,
            CourseRepository courseRepository, CourseService courseService, StudentRepository studentRepository) {
        this.courseMapper = courseMapper;
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<At> getActiveTeams() {
        return teamRepository
                .findAll()
                .stream()
                .map(team -> {
                    At assignTeamToStudentDTO = teamMapper.teamToAssign(team);

                    return assignTeamToStudentDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamRespponseDTO> getAllTeam() {

        return teamRepository
                .findAll()
                .stream()
                .map(team -> {
                    TeamRespponseDTO teamRespponseDTO = teamMapper.teamToResponse(team);
                    teamRespponseDTO.setTeamId(team.getId());
                    return teamRespponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TeamRespponseDTO getTeamsById(Long id) {

        Team team = teamRepository.findById(id).get();
        Course course = courseRepository.findById(team.getCourse().getId()).get();
        CourseDTO courseDTO = courseMapper.courseToCourseDTO(course);
        TeamRespponseDTO team1 = teamMapper.teamToResponse(team);
        team1.setTeamId(team.getId());
        team1.setCourse(courseDTO);

        return team1;

    }

    @Override
    @Transactional
    public At assignStudentToTeam(Long teamId, Long studentId) {
        Team team = teamRepository.findById(teamId).get();
        if (team.isDeleted() == true) {
            throw new RuntimeException("team is deactivated");
        }
        Student student = studentRepository.findById(studentId).get();
        if(student.isActive()){
        team.getStudents().add(student);
      
        if (team.getMaxstudentsenrolled() >= team.getStudents().size()) {
            At assign = new At();
            assign = teamMapper.teamToAssign(team);

            return assign;
        } else {
            throw new RuntimeException("Team is full");
        }
        } else {
          throw new RuntimeException("Student is deactive");   
        }
    }

    @Override
    @Transactional
    public TeamRespponseDTO createNewTeam(TeamDTO teamDTO) {
        Optional<Team> teamName = teamRepository.findByTeamname(teamDTO.getTeamname());
        if (teamName.isPresent()) {
          throw new RuntimeException ("Teamname already exists");   
        } else {
        Course course = courseRepository.findById(teamDTO.getCourseId()).get();
        Team team = teamMapper.teamDtoToteam(teamDTO);
        team.setCourse(course);
        if(team.getStarttime().plusMonths(6).isBefore(team.getEndtime())){
        team = teamRepository.save(team);

        CourseDTO coursedto = courseMapper.courseToCourseDTO(course);

        TeamRespponseDTO team1 = teamMapper.teamToResponse(team);
        team1.setTeamId(team.getId());
        team1.setCourse(coursedto);

        return team1;
        } else {
         throw new RuntimeException("enddate must be 6 months after startdate");   
        }
    }
    }

    @Override
    @Transactional
    public TeamRespponseDTO updateTeam(Long id, TeamDTO teamDTO) {
        Course course = courseRepository.findById(teamDTO.getCourseId()).get();
        Team team = teamRepository.findById(id).get();
        if (team.isDeleted() == true || team.isFinished() == true) {
            throw new RuntimeException("Team is deleted or finished");
        } else {
            team.setTeamname(teamDTO.getTeamname());
            team.setEndtime(teamDTO.getEndtime());
            team.setStarttime(team.getStarttime());
            team.setMaxstudentsenrolled(teamDTO.getMaxstudentsenrolled());
            team.setCourse(course);

            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setName(course.getName());
            courseDTO.setDescription(course.getDescription());

            TeamRespponseDTO teamResponse = new TeamRespponseDTO();
            teamResponse.setMaxstudentsenrolled(team.getMaxstudentsenrolled());
            teamResponse.setTeamname(team.getTeamname());
            teamResponse.setStarttime(team.getStarttime());
            teamResponse.setEndtime(team.getEndtime());
            teamResponse.setCourse(courseDTO);
            teamResponse.setTeamId(team.getId());
            teamResponse.setDeleted(team.isDeleted());
            teamResponse.setDeleted(team.isFinished());
            return teamResponse;
        }
    }

    @Override
    @Transactional
    public void deleteTeamById(Long id) {
        Optional<Team> teamOPT = teamRepository.findById(id);

        Team team1 = teamOPT.get();

        if (team1.isDeleted() == true) {
            teamRepository.deleteById(team1.getId());
        } else if (team1.getStudents().isEmpty()) {
            teamRepository.deleteById(team1.getId());
        } else {
            throw new IllegalArgumentException("Team is active");
        }
    }

    @Override
    @Transactional
    public void softDeleteTeamById(Long teamId) {
        Optional<Team> teamOPT = teamRepository.findById(teamId);
        Team team1 = teamOPT.get();
        if (team1.getStudents() != null) {
            team1.setDeleted(true);
            teamRepository.save(team1);
        } else {
            throw new RuntimeException("Team can be hard deleted");
        }

    }
}
