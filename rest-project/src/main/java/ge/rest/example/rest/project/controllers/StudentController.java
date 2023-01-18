/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.StudentListDTO;
import ge.rest.example.rest.project.model.StudentResponseDTO;
import ge.rest.example.rest.project.services.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
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
    public StudentListDTO getListOfStudent() {
        return new StudentListDTO(studentService.getAllStudents());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO createNewStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return studentService.createNewStudent(studentDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDTO updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping({"/deactive/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deactiveStudentById(@PathVariable Long id) {
        studentService.deactiveStudentById(id);
    }
    @DeleteMapping({"/deleteDeactivated/{id}"})
    @ResponseStatus(HttpStatus.OK)
     public  void deleteDeactiveateddStudent(Long id) {
          studentService.deleteDeactiveateddStudent(id);
     }
}
