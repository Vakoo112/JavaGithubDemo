/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author vako
 */
@Data
public class CourseDTO {

    @NotBlank(message = "Coursename cannot be blank")
    @Length(min = 5, max = 16, message = "Coursename must be between 5-16 characters")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    @Length(min = 10, max = 30, message = "Description must be between 5-16 characters")
    private String description;

}
