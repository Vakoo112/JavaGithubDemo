/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.repositories;

import ge.rest.example.rest.project.domain.Returntype;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vako
 */
public interface ReturnRepo extends JpaRepository<Returntype,Long> {
    
}
