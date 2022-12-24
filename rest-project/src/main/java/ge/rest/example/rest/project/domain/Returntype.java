/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author vako
 */
@Data
@Entity
public class Returntype {

    @Id
    @Column(name = "newid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newid;
    
      private String name;

    private String description;

    private LocalDate starttime;
    private LocalDate endtime;

    private Integer maxstudentsenrolled;
}
