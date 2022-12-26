package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.CourseListDTO;
import ge.rest.example.rest.project.model.ReturnTypeDTO;
import ge.rest.example.rest.project.model.ReturnTypeListDTO;
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
    @Operation(summary = "Search active course ", description = "Activecourse  shesaxeb ")
    @GetMapping({"/{newid}/activity"})
    @ResponseStatus(HttpStatus.OK)
    public ReturnTypeDTO getActiveTeamById(@PathVariable Long newid) {
        return courseService.getActive(newid);
    }
      @Operation(summary = "Active courses", description = "Activecourse  shesaxeb ")
     @GetMapping({"/activecourses"})
    @ResponseStatus(HttpStatus.OK)
    public ReturnTypeListDTO getActives() {
        return new ReturnTypeListDTO(courseService.getAllActive());
    }
     @Operation(summary = "add teamToCourse ", description = "Activecourse  shesaxeb ")
    @PostMapping({"/{teamId}/contact/{courseId}"})
    @ResponseStatus(HttpStatus.OK)
    public ReturnTypeDTO assignTeamToocourse(@PathVariable Long teamId, @PathVariable Long courseId) {
        return courseService.assignTeamTocourse(teamId, courseId);
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
   @Operation(summary = "Update ActiveCourse ", description = "Activecourse  shesaxeb ")
   @PutMapping({"/{newid}/update"})
   @ResponseStatus(HttpStatus.OK)
   public ReturnTypeDTO updateActive(@PathVariable Long newid, @RequestBody ReturnTypeDTO returnTypeDTO){
       return courseService.updateActive(newid, returnTypeDTO);
   }
    @Operation(summary = "delete Course ", description = "course  shesaxeb ")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }
    
     @Operation(summary = "delete ActiveCourse ", description = "Activecourse  shesaxeb ")
    @DeleteMapping({"/activeCourse/{newid}"})
    @ResponseStatus(HttpStatus.OK)
    public void DeleteActiveCourseById(@PathVariable Long newid) {
        courseService.DeleteActiveCourseById(newid);
    }
    
}
