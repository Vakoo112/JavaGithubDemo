/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class AssignDTO {

    private String teamname;
    private LocalDate starttime;
    private LocalDate endtime;

    private Integer maxstudentsenrolled;
    private Long courseId;
    private Set<StudentDTO> students;
}