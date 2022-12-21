/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author vako
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"students"})
@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private LocalDate  starttime;
    private LocalDate  endtime;
    private Integer maxstudentsenrolled;
    
    @ManyToOne
    private Course course; 
    
    @ManyToMany(mappedBy="teams")
    private Set<Student> students;
    
    @Enumerated(value=EnumType.STRING)
    private Quality quality;
}
