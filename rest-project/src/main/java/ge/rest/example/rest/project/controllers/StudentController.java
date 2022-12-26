/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.StudentListDTO;
import ge.rest.example.rest.project.model.StudentReturnTypeDTO;
import ge.rest.example.rest.project.services.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vako
 */
@Tag(name = "This is my Student Controller")
@RestController
@RequestMapping(StudentController.BASE_URL)
public class StudentController {
    public static final String BASE_URL = "/api/v1/students";
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StudentListDTO getListOfStudent(){
        return new StudentListDTO(studentService.getAllStudents());
    }
 
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createNewStudent(@RequestBody StudentDTO studentDTO){
        return studentService.createNewStudent(studentDTO);
    }
    @PostMapping({"/{contactId}/student/{studentId}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentReturnTypeDTO assignTeamToocourse(@PathVariable Long contactId, @PathVariable Long studentId) {
        return studentService.addContactToStudent(contactId, studentId);
    }
     @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return studentService.saveStudentByDTO(id, studentDTO);
    }
    
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }
}