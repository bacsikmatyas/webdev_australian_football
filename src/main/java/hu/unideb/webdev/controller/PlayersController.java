package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.PlayersDeleteDto;
import hu.unideb.webdev.controller.dto.PlayersDto;
import hu.unideb.webdev.exceptions.ExistingPlayerException;
import hu.unideb.webdev.exceptions.UnknownPlayerException;
import hu.unideb.webdev.exceptions.WrongValueException;
import hu.unideb.webdev.model.Players;
import hu.unideb.webdev.service.PlayersService;
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
public class PlayersController {
    private final PlayersService service;

    @GetMapping("/players")
    public Collection<PlayersDto> listPlayers(){
        return service.getAllPlayers().stream()
                .map(model -> new PlayersDto(
                        model.getId(),
                        model.getDob().toString(),
                        model.getFirstName(),
                        model.getLastName(),
                        model.getHeight(),
                        model.getWeight()
                ))
                .collect(Collectors.toList());
    }

    @PostMapping("/players")
    public void recordPlayer(@RequestBody PlayersDto playersDto){
        try {
            service.recordPlayer(new Players(
                    playersDto.getId(),
                    Timestamp.valueOf(playersDto.getDob()),
                    playersDto.getFirstName(),
                    playersDto.getLastName(),
                    playersDto.getHeight(),
                    playersDto.getWeight()
            ));
        } catch (ExistingPlayerException | WrongValueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/players/update")
    public void updatePlayer(@RequestBody PlayersDto playersDto){
        try {
            service.updatePlayer(new Players(
                    playersDto.getId(),
                    Timestamp.valueOf(playersDto.getDob()),
                    playersDto.getFirstName(),
                    playersDto.getLastName(),
                    playersDto.getHeight(),
                    playersDto.getWeight()
            ));
        } catch (UnknownPlayerException | WrongValueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/players")
    public void deletePlayer(@RequestBody PlayersDeleteDto playersDeleteDto){
        try {
            service.deletePlayer(playersDeleteDto.getId());
        } catch (UnknownPlayerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
