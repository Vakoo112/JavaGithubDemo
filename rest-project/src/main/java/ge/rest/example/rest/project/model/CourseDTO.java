/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.rest.example.rest.project.domain.Team;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class CourseDTO {

    private String name;

    private String description;
    
 //   @JsonProperty("courseurl")
   // private String courseUrl;
      
}
