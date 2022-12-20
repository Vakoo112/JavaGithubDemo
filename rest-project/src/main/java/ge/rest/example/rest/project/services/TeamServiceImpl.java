/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.domain.Team;
import ge.rest.example.rest.project.mapper.TeamMapper;
import ge.rest.example.rest.project.model.TeamDTO;
import ge.rest.example.rest.project.repositories.TeamRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author vako
 */
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamMapper teamMapper, TeamRepository teamRepository) {
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
    }
    
     @Override
   public    List<TeamDTO> getAllTeam() {
        return teamRepository
                .findAll()
                .stream()
                .map(team -> {
                   TeamDTO teamDTO = teamMapper.teamToTeamDTO(team);
                   return teamDTO;
                })
                .collect(Collectors.toList());
    }
    
     @Override
    public TeamDTO getTeamsById(Long id) {

        return teamRepository.findById(id)
                .map(teamMapper::teamToTeamDTO)
                .map(teamDTO -> {
                   
                    return teamDTO;
                })
                .orElseThrow(RuntimeException::new);
        
    }
     private TeamDTO saveAndReturnDTO(Team team) {
        Team savedTeam = teamRepository.save(team);

        TeamDTO returnDto = teamMapper.teamToTeamDTO(savedTeam);


        return returnDto;
    }
    
       @Override
    public TeamDTO saveCTeamByDTO(Long id, TeamDTO teamDTO) {
        Team team = teamMapper.teamDtoToteam(teamDTO);
        team.setId(id);
        return saveAndReturnDTO(team);
    }
    
       @Override
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }
}
