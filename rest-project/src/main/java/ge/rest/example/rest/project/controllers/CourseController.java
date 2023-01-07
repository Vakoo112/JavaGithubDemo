package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.CourseListDTO;
import ge.rest.example.rest.project.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "This is my Course Controller")
@RestController
@RequestMapping(CourseController.BASE_URL)
public class CourseController {

    public static final String BASE_URL = "/api/v1/courses";
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Get Course ", description = "course  shesaxeb ")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CourseListDTO getallCourses() {

        return new CourseListDTO(courseService.getAllCourses());
    }

    @Operation(summary = "Get Course by id ", description = "course  shesaxeb ")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO getTeamById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @Operation(summary = "add teamToCourse ", description = "Activecourse  shesaxeb ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO createNewTeam(@RequestBody CourseDTO courseDTO) {
        return courseService.createNewCourse(courseDTO);
    }

    @Operation(summary = "update Course ", description = "course  shesaxeb ")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return courseService.saveCourseByDTO(id, courseDTO);
    }

    @Operation(summary = "delete Course ", description = "course  shesaxeb ")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }

}
