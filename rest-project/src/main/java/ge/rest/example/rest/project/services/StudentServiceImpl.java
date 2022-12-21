/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.controllers.StudentController;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.mapper.StudentMapper;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.repositories.StudentRepository;
import java.util.List;
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

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }
   
     @Override
    public  List<StudentDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(student -> {
                   StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);
                 //  studentDTO.setStudentUrl(getStudentUrl(student.getId()));
                   return studentDTO;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public StudentDTO getStudentById(Long id) {

        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentDTO)
                .map(studentDTO -> {
                   
                  //  studentDTO.setStudentUrl(getStudentUrl(id));
                    return studentDTO;
                })
                .orElseThrow(RuntimeException::new);
    }
    @Override
    public StudentDTO createNewStudent(StudentDTO studentDTO) {

        return saveAndReturnDTO(studentMapper.studentDtoTostudent(studentDTO));
    }
    
      private StudentDTO saveAndReturnDTO(Student student) {
        Student savedStudent = studentRepository.save(student);

        StudentDTO returnDto = studentMapper.studentToStudentDTO(savedStudent);

       // returnDto.setStudentUrl(getStudentUrl(savedStudent.getId()));

        return returnDto;
    }
      
      @Override
    public StudentDTO saveStudentByDTO(Long id, StudentDTO studentDTO) {
        Student student = studentMapper.studentDtoTostudent(studentDTO);
        student.setId(id);
        return saveAndReturnDTO(student);
    }
    
    
     private String getCustomerUrl(Long id) {
        return StudentController.BASE_URL + "/" + id;
    }
     
     @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

   
}
