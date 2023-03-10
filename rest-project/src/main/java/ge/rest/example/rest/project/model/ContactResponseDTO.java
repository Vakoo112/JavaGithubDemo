/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import ge.rest.example.rest.project.domain.ContactType;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class ContactResponseDTO {
    private ContactType contacttype;
    private String contactvalue;

    private StudentDTO student;
    private Long studentId;
    private Long contactId;
}
