package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.ExistingTeamException;
import hu.unideb.webdev.exceptions.UnknownTeamException;
import hu.unideb.webdev.model.Teams;

import java.util.Collection;

public interface TeamsService {
    Collection<Teams> getAllTeams();

    void recordTeam(Teams team) throws ExistingTeamException;
    void updateTeam(Teams team) throws UnknownTeamException;
    void deleteTeam(int id) throws UnknownTeamException;
}
