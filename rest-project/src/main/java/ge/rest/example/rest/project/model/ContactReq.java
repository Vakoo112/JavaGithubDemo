/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import ge.rest.example.rest.project.domain.ContactType;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author vako
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactReq {
    private ContactType contacttype;
    @NotBlank(message = "ContactValue cannot be blank")
    @Length(min = 9, max = 30, message = "ContactValue must be between 9-16 characters")
    private String contactvalue;
    private Long studentId;
}
