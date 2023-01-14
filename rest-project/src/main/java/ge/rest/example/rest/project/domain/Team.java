/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author vako
 */

@Getter
@Setter
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   
    private String teamname;
    private LocalDate starttime;
    private LocalDate endtime;
   // @Min(value= 5, message="Minimum must be 5")
   // @Max(value= 15, message="Maximum must be 15")
    private Integer maxstudentsenrolled;
    private boolean finished=Boolean.FALSE;
    private boolean deleted = Boolean.FALSE;
    @ManyToOne
    private Course course;
    //add detatch
    @ManyToMany
     @JoinTable(name="student_team", 
            joinColumns=@JoinColumn(name ="team_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();
    
   

}
