/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author vako
 */
@Getter
@Setter
@Entity
public class Course {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
   
    private String description;
     
    private String name;
   
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="course")
    private Set<Team> teams = new HashSet<>();

    
    public void addTea(Team team) {
      teams.add(team);
    }
    
    public void removeTeam(Team team) {
      teams.remove(team);
    }
    
   
}
