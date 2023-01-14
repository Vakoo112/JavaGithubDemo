/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.At;
import ge.rest.example.rest.project.model.ListOfAt;
import ge.rest.example.rest.project.model.TeamDTO;
import ge.rest.example.rest.project.model.TeamRespponseDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface TeamService {
    
    List<At> getActiveTeams();

    List<TeamRespponseDTO> getAllTeam();

    TeamRespponseDTO getTeamsById(Long id);
    
     At assignStudentToTeam(Long teamId, Long studentId);

    TeamRespponseDTO createNewTeam(TeamDTO teamDTO);

    TeamRespponseDTO updateTeam(Long id, TeamDTO teamDTO);

    void deleteTeamById(Long id);
    
    void softDeleteTeamById(Long teamId);

}
