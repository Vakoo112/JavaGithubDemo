/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class ReturnTypeDTO {
    // private  CourseDTO courseDTO;
    // private TeamDTO teamDTO;

    //private Team team;
    //private Course course;
    private Long newid;
    private String name;

    private String description;

    private LocalDate starttime;
    private LocalDate endtime;

    private Integer maxstudentsenrolled;

}
