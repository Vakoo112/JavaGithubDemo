/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.domain.Team;
import ge.rest.example.rest.project.model.AssignDTO;
import ge.rest.example.rest.project.model.TeamDTO;
import ge.rest.example.rest.project.model.TeamRespponseDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface TeamService {
    List<TeamRespponseDTO> getAllTeam();

    TeamRespponseDTO getTeamsById(Long id);
    
    AssignDTO assignStudentToTeam (Long teamId, Long studentId);
    
    TeamRespponseDTO createNewTeam(TeamDTO teamDTO);
    
    TeamRespponseDTO updateTeam(Long id,TeamDTO teamDTO);
    
 //   TeamDTO saveCTeamByDTO(Long id, TeamDTO teamDTO);
    
   
    
    void deleteTeamById(Long id);
    
   
}
