/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author vako
 */
@Data
public class TeamDTO {


    @NotBlank(message = "Team name cannot be blank")
    @Length(min = 5, max = 16, message = "Team name must be between 5-16 characters")
    private String teamname;
    private LocalDate starttime;
    private LocalDate endtime;
    @Min(value = 5, message = "Minimum must be 5")
    @Max(value = 15, message = "Maximum must be 15")
    private Integer maxstudentsenrolled;
    private Long courseId;

}
