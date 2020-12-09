package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.ExistingPlayerException;
import hu.unideb.webdev.exceptions.UnknownPlayerException;
import hu.unideb.webdev.model.Players;

import java.util.Collection;

public interface PlayersService {
    Collection<Players> getAllPlayers();

    void recordPlayer(Players player) throws ExistingPlayerException;
    void updatePlayer(Players player) throws UnknownPlayerException;
    void deletePlayer(int id) throws UnknownPlayerException;
}
