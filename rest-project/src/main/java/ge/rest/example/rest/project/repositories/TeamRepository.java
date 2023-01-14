/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.repositories;

import ge.rest.example.rest.project.domain.Team;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vako
 */
@SQLDelete(sql = "UPDATE Team SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public interface TeamRepository extends JpaRepository<Team,Long> {
}
