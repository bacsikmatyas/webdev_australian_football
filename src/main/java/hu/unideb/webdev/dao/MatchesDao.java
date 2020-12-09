package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownMatchException;
import hu.unideb.webdev.exceptions.UnknownTeamException;
import hu.unideb.webdev.model.Matches;

import java.util.Collection;

public interface MatchesDao {

    void createMatch(Matches match) throws UnknownTeamException;

    Collection<Matches> readAll();

    void updateMatch(Matches match) throws UnknownMatchException;

    void deleteMatch(String id) throws UnknownMatchException;

}
