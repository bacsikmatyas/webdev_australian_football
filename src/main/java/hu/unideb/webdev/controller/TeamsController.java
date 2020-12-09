package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.PlayersDeleteDto;
import hu.unideb.webdev.controller.dto.PlayersDto;
import hu.unideb.webdev.controller.dto.TeamsDeleteDto;
import hu.unideb.webdev.controller.dto.TeamsDto;
import hu.unideb.webdev.exceptions.ExistingPlayerException;
import hu.unideb.webdev.exceptions.ExistingTeamException;
import hu.unideb.webdev.exceptions.UnknownPlayerException;
import hu.unideb.webdev.exceptions.UnknownTeamException;
import hu.unideb.webdev.model.Players;
import hu.unideb.webdev.model.Teams;
import hu.unideb.webdev.service.TeamsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TeamsController {
    private final TeamsService service;

    @GetMapping("/teams")
    public Collection<TeamsDto> listTeams(){
        return service.getAllTeams().stream()
                .map(model -> new TeamsDto(
                        model.getId(),
                        model.getName()
                ))
                .collect(Collectors.toList());
    }

    @PostMapping("/teams")
    public void recordTeams(@RequestBody TeamsDto teamsDto){
        try {
            service.recordTeam(new Teams(
                    teamsDto.getId(),
                    teamsDto.getName()
            ));
        } catch (ExistingTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/teams/update")
    public void updateTeam(@RequestBody TeamsDto teamsDto){
        try {
            service.updateTeam(new Teams(
                    teamsDto.getId(),
                    teamsDto.getName()
            ));
        } catch (UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/teams")
    public void deleteTeam(@RequestBody TeamsDeleteDto teamsDeleteDto){
        try {
            service.deleteTeam(teamsDeleteDto.getId());
        } catch (UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
