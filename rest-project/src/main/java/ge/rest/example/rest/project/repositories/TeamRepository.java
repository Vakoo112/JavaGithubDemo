/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.repositories;

import ge.rest.example.rest.project.domain.Course;
import ge.rest.example.rest.project.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vako
 */
public interface TeamRepository extends JpaRepository<Team,Long> {
    Course findByid(Long id);
}
