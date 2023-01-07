/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.controllers.StudentController;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.domain.Team;
import ge.rest.example.rest.project.mapper.StudentMapper;
import ge.rest.example.rest.project.model.AssignTeamToStudentDTO;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.repositories.ContactRepository;
import ge.rest.example.rest.project.repositories.StudentRepository;
import ge.rest.example.rest.project.repositories.TeamRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author vako
 */
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final ContactRepository contactRepository;
    private final TeamRepository teamRepository;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository,
    ContactRepository contactRepository,TeamRepository teamRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.contactRepository = contactRepository;
        this.teamRepository = teamRepository;
    }
   
     @Override
    public  List<StudentDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(student -> {
                   StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);
                   return studentDTO;
                })
                .collect(Collectors.toList());
    }

    
    @Override
    public StudentDTO getStudentById(Long id) {

        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentDTO)
                .map(studentDTO -> {
                   
                    return studentDTO;
                })
                .orElseThrow(RuntimeException::new);
    }
     @Override
    public  AssignTeamToStudentDTO assignStudentToTeam (Long studentId, Long teamId){
        Set<Team> teamSet = null;
        Team team = teamRepository.findById(teamId).get();
        Student student = studentRepository.findById(studentId).get();
        teamSet = student.getTeams();
        teamSet.add(team);
        student.setTeams(teamSet);
        teamRepository.save(team);
        studentRepository.save(student);
        AssignTeamToStudentDTO assign = new AssignTeamToStudentDTO();
        assign = studentMapper.studentToAssign(student);
        assign.setContactId(student.getId());
        return assign;
        
        
    }
    
    @Override
    public StudentDTO createNewStudent(StudentDTO studentDTO) {

        return saveAndReturnDTO(studentMapper.studentDtoTostudent(studentDTO));
    }
   
    
      private StudentDTO saveAndReturnDTO(Student student) {
        Student savedStudent = studentRepository.save(student);
        
        StudentDTO returnDto = studentMapper.studentToStudentDTO(savedStudent);
        return returnDto;
    }
      
      @Override
    public StudentDTO saveStudentByDTO(Long id, StudentDTO studentDTO) {
        Student student = studentMapper.studentDtoTostudent(studentDTO);
        student.setId(id);
        return saveAndReturnDTO(student);
    }
    

    
    
     private String getStudentUrl(Long id) {
        return StudentController.BASE_URL + "/" + id;
    }
     
     @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

   
}