package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.TeamsDao;
import hu.unideb.webdev.exceptions.ExistingTeamException;
import hu.unideb.webdev.exceptions.UnknownTeamException;
import hu.unideb.webdev.exceptions.WrongValueException;
import hu.unideb.webdev.model.Teams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamsServiceImpl implements TeamsService {
    private final TeamsDao teamsDao;

    @Override
    public Collection<Teams> getAllTeams() {
        return teamsDao.readAll();
    }

    @Override
    public void recordTeam(Teams team) throws ExistingTeamException, WrongValueException {
        if (team.getId()<=100){
            throw new WrongValueException(String.format("Team ID is less than or equal to 100, ID: %d",team.getId()));
        }
        teamsDao.createTeam(team);
    }

    @Override
    public void updateTeam(Teams team) throws UnknownTeamException, WrongValueException {
        if (team.getId()<=100){
            throw new WrongValueException(String.format("Team ID is less than or equal to 100, ID: %d",team.getId()));
        }
        teamsDao.updateTeam(team);
    }

    @Override
    public void deleteTeam(int id) throws UnknownTeamException {
        teamsDao.deleteTeam(id);
    }
}
