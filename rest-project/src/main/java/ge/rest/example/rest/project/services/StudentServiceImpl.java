/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.controllers.RestResponseEntityExceptionHandler;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.mapper.StudentMapper;
import ge.rest.example.rest.project.mapper.TeamMapper;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.StudentResponseDTO;
import ge.rest.example.rest.project.repositories.ContactRepository;
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
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final TeamMapper teamMapper;
    private final StudentRepository studentRepository;
    private final ContactRepository contactRepository;
    private final TeamRepository teamRepository;

    public StudentServiceImpl(StudentMapper studentMapper,TeamMapper teamMapper, StudentRepository studentRepository,
    ContactRepository contactRepository,TeamRepository teamRepository, RestResponseEntityExceptionHandler 
            restResponseEntityExceptionHandler) {
        this.studentMapper = studentMapper;
        this.teamMapper=teamMapper;
        this.studentRepository = studentRepository;
        this.contactRepository = contactRepository;
        this.teamRepository = teamRepository;
    }
    
    
   

     @Override
    public  List<StudentResponseDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(student -> {
                   StudentResponseDTO studentResponseDTO = studentMapper.studentToREsponse(student);
                   return studentResponseDTO;
                })
                .collect(Collectors.toList());
    }

    
    @Override
    public StudentResponseDTO getStudentById(Long id) {

        Optional<Student> studentOPT = studentRepository.findById(id);
        Student student = studentOPT.get();
        
        StudentResponseDTO response = new StudentResponseDTO();
        response = studentMapper.studentToREsponse(student);
        return response;
    }
   
   
    
    @Override
    @Transactional
    public StudentResponseDTO createNewStudent(StudentDTO studentDTO) {
     
      Student student = new Student();
      student.setAdress(studentDTO.getAdress());
      student.setFirstname(studentDTO.getFirstname());
      student.setLastname(studentDTO.getLastname());
      student.setIdnumber(studentDTO.getIdnumber());
     
      studentRepository.save(student);
      StudentResponseDTO response = new StudentResponseDTO();
      response.setAdress(student.getAdress());
      response.setFirstname(student.getFirstname());
      response.setLastname(student.getLastname());
      response.setIdnumber(student.getIdnumber());
      response.setId(student.getId());
      
     
      return response;
      
    }
   
   @Override
   @Transactional
   public StudentResponseDTO updateStudent(Long id, StudentDTO studentDTO) {
       Student student = studentRepository.findById(id).get();
       student.setAdress(studentDTO.getAdress());
       student.setFirstname(studentDTO.getFirstname());
       student.setLastname(studentDTO.getLastname());
       student.setIdnumber(studentDTO.getIdnumber());
       
       StudentResponseDTO response = new StudentResponseDTO();
       response.setAdress(student.getAdress());
       response.setFirstname(student.getFirstname());
       response.setLastname(student.getLastname());
       response.setIdnumber(student.getIdnumber());
       response.setId(student.getId());
       return response;
   }
   
     @Override
     @Transactional
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

   
}