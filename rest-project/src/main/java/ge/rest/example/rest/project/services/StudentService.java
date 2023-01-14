/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.StudentResponseDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface StudentService {

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(Long id);

    StudentResponseDTO createNewStudent(StudentDTO studentDTO);
    
     StudentResponseDTO updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudentById(Long id);

}
