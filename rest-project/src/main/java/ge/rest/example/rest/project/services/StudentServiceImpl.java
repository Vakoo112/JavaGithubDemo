/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.controllers.StudentController;
import ge.rest.example.rest.project.domain.Contact;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.domain.Studentreturntype;
import ge.rest.example.rest.project.mapper.StudentMapper;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.StudentReturnTypeDTO;
import ge.rest.example.rest.project.repositories.ContactRepository;
import ge.rest.example.rest.project.repositories.StudentRepository;
import ge.rest.example.rest.project.repositories.StudentReturnRepo;
import java.util.List;
import java.util.Optional;
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
    private final StudentReturnRepo studentReturnRepo;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository,
    ContactRepository contactRepository, StudentReturnRepo studentReturnRepo) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.contactRepository = contactRepository;
        this.studentReturnRepo=studentReturnRepo;
    }
   
     @Override
    public  List<StudentDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(student -> {
                   StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);
                 studentDTO.setStudentUrl(getStudentUrl(student.getId()));
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
    public StudentDTO createNewStudent(StudentDTO studentDTO) {

        return saveAndReturnDTO(studentMapper.studentDtoTostudent(studentDTO));
    }
     @Override
     public StudentReturnTypeDTO addContactToStudent(Long contactId, Long studentId){
         Optional<Contact> contactOpt = contactRepository.findById(contactId);
         if (!contactOpt.isPresent()) {
              throw new ResourceNotFoundException();
         }
         Contact contactGet = contactOpt.get();
         contactRepository.save(contactGet);
         
         Optional<Student> studentOpt = studentRepository.findById(studentId);
         if(!studentOpt.isPresent()) {
          throw new ResourceNotFoundException();
         }
         Student studentGet = studentOpt.get();
         studentRepository.save(studentGet);
         StudentReturnTypeDTO allToAll = studentMapper.allToAll(contactGet, studentGet);
         Studentreturntype change = studentMapper.studentDTOtoType(allToAll);
         //
         studentReturnRepo.save(change);
         return  allToAll;
         
     }
    
      private StudentDTO saveAndReturnDTO(Student student) {
        Student savedStudent = studentRepository.save(student);
        
        StudentDTO returnDto = studentMapper.studentToStudentDTO(savedStudent);
           returnDto.setStudentUrl(getStudentUrl(savedStudent.getId()));
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