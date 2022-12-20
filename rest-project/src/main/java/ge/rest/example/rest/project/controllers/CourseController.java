/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.CourseListDTO;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.services.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vako
 */
@Api(description="This is my Course Controller")
@RestController
@RequestMapping(CourseController.BASE_URL)
public class CourseController {
     public static final String BASE_URL = "/api/v1/courses";
     private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @ApiOperation(value="Get courses", notes = "course shesaxeb") 
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CourseListDTO getallCourses(){

        return new CourseListDTO
                 (courseService.getAllCourses());
    }
    @ApiOperation(value="Get course by name", notes = "name shesaxeb")
     @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO getCourseByName(@PathVariable String name){
        return courseService.getCourseByName(name);
    }
    @ApiOperation(value="Update course", notes = "update shesaxeb")
     @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO){
        return courseService.saveCourseByDTO(id, courseDTO);
    }
    @ApiOperation(value="Delete course", notes = "delete shesaxeb")
     @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id){
        courseService.deleteStudentById(id);
    }
}
