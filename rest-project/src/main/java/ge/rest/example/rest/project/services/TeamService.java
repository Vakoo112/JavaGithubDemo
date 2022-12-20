/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.TeamDTO;
import ge.rest.example.rest.project.model.TeamListDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface TeamService {
    List<TeamDTO> getAllTeam();

    TeamDTO getTeamsById(Long id);
    
    TeamDTO saveCTeamByDTO(Long id, TeamDTO teamDTO);
    
    void deleteTeamById(Long id);
}
