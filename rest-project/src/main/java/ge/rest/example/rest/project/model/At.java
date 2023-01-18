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
public class At {
      private long id;
    private String teamname;
    private LocalDate starttime;
    private LocalDate endtime;
    private Integer maxstudentsenrolled;
    private boolean finished = Boolean.FALSE;
    private boolean deleted = Boolean.FALSE;
    private CourseDTO course;
    private Set<StudentResponseDTO> students;
}