/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.CourseListDTO;
import ge.rest.example.rest.project.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vako
 */
@RestController
@RequestMapping(CourseController.BASE_URL)
public class CourseController {
     public static final String BASE_URL = "/api/v1/courses";
     private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
     
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CourseListDTO getallCourses(){

        return new CourseListDTO
                 (courseService.getAllCourses());
    }
     @GetMapping("{description}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO getCourseByName(@PathVariable String description){
        return courseService.getCourseByName(description);
    }
}
