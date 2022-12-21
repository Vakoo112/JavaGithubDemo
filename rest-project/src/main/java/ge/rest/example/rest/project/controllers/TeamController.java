/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.TeamDTO;
import ge.rest.example.rest.project.model.TeamListDTO;
import ge.rest.example.rest.project.services.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vako
 */

@Api(description="This is my Team Controller")
@RestController
@RequestMapping(TeamController.BASE_URL)
public class TeamController {
    public static final String BASE_URL = "/api/v1/teams";
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @ApiOperation(value="Get all teams", notes = "teamebis shesaxeb")
     @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TeamListDTO getAllTeam(){
        
        return new TeamListDTO
                 (teamService.getAllTeam());
    }
    @ApiOperation(value="Get team by id ", notes = "team id shesaxeb")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO getTeamById(@PathVariable Long id){
        return teamService.getTeamsById(id);
    }
      @ApiOperation(value="This will create a new team", notes = "Api creates shesaxeb")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createNewTeam(@RequestBody TeamDTO teamDTO){
        return teamService.createNewTeam(teamDTO);
    }
    @ApiOperation(value="Update team", notes = " update shesaxeb")
     @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO){

        return teamService.saveCTeamByDTO(id, teamDTO);
    }
    @ApiOperation(value="Delte team", notes = "delete shesaxeb")
        @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable Long id){
        teamService.deleteTeamById(id);
    }
}
