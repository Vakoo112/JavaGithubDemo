/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author vako
 */
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String idnumber;
    private String adress;
    private boolean active=Boolean.TRUE;
    @OneToMany( mappedBy="student")
    private Set<Contact> contacts = new HashSet<>();
    @ManyToMany(mappedBy = "students")
    private Set<Team> teams = new HashSet<>();
}