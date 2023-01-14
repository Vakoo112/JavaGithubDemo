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
public class CourseResponseDTO {

    private String name;

    private String description;
    
    private Long courseId;
}
