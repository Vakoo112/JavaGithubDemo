/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
public class TeamDTO {
    private Long id;
    private Date starttime;
    private Date endtime;
    private Integer maxstudentsenrolled;
}
