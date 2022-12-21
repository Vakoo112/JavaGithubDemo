/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import ge.rest.example.rest.project.domain.Team;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Set<Team> teams;
}
