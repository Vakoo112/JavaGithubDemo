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
public class TeamRespponseDTO {

    private String teamname;
    private LocalDate starttime;
    private LocalDate endtime;

    private Integer maxstudentsenrolled;
    

    private boolean finished;
    private boolean deleted;
    private Long teamId;

    private CourseDTO course;
}
