/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vako
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    
    private String firstname;
    private String lastname;
    @JsonProperty("student_url")
    private String studentUrl;
}
