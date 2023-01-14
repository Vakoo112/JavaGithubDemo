/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.mapper;

import ge.rest.example.rest.project.domain.Team;
import ge.rest.example.rest.project.model.AssignDTO;
import ge.rest.example.rest.project.model.At;
import ge.rest.example.rest.project.model.TeamDTO;
import ge.rest.example.rest.project.model.TeamRespponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author vako
 */
@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    
    TeamDTO teamToTeamDTO(Team team);
    
    Team teamDtoToteam(TeamDTO teamDTO);
    
    TeamRespponseDTO teamToResponse(Team team);
    
    AssignDTO teamtoAssign(Team team);
    
    At  teamToAssign(Team team);

}
