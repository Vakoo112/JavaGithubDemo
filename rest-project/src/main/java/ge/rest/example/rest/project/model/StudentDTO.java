/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 *
 * @author vako
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @NonNull
    @NotBlank
    @Size(min = 2, max = 25)
    private String firstname;
    @NonNull
    @NotBlank
    @Size(min = 2, max = 25)
    private String lastname;
    @NonNull
    @NotBlank
    @Size(min = 2, max = 11)
    private String idnumber;
    @NonNull
    @NotBlank
    @Size(min = 2, max = 25)
    private String adress;
    //contact iyo
    @JsonProperty("student_url")
    private String studentUrl;

}
