/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class StudentReturnTypeDTO {

    private Long newid;
    private String firstname;
    private String lastname;
    private String idnumber;
    private String adress;
    private String contacttype;
    private String contactvalue;
}
