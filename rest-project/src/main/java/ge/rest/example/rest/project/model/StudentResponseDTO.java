/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import java.util.Set;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class StudentResponseDTO {
     private Long id;
    private String firstname;
    private String lastname;
    private String idnumber;
    private String adress;
    private boolean active;
    private Set<ContactResponseForList> contacts;
}
