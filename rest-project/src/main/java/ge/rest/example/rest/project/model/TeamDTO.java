/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import ge.rest.example.rest.project.domain.Student;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class TeamDTO {
    private Long id;
    private LocalDate  starttime;
    private LocalDate  endtime;
    private Integer maxstudentsenrolled;
    private Set<Student> students;
}
