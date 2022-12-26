/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.StudentReturnTypeDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createNewStudent(StudentDTO studentDTO);

    StudentReturnTypeDTO addContactToStudent(Long contactId, Long studentId);
    

    StudentDTO saveStudentByDTO(Long id, StudentDTO studentDTO);

    void deleteStudentById(Long id);
}
