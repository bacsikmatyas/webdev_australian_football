package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.PlayersDao;
import hu.unideb.webdev.exceptions.ExistingPlayerException;
import hu.unideb.webdev.exceptions.UnknownPlayerException;
import hu.unideb.webdev.exceptions.WrongValueException;
import hu.unideb.webdev.model.Players;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayersServiceImpl implements PlayersService {

    private final PlayersDao playersDao;

    @Override
    public Collection<Players> getAllPlayers() {
        return playersDao.readAll();
    }

    @Override
    public void recordPlayer(Players player) throws ExistingPlayerException, WrongValueException {
        if (player.getId()<=10000){
            throw new WrongValueException(String.format("Player ID is less than or equal to 10000, ID: %d",player.getId()));
        }
        playersDao.createPlayer(player);
    }

    @Override
    public void updatePlayer(Players player) throws UnknownPlayerException, WrongValueException {
        if (player.getId()<=10000){
            throw new WrongValueException(String.format("Player ID is less than or equal to 10000, ID: %d",player.getId()));
        }
        playersDao.updatePlayer(player);
    }

    @Override
    public void deletePlayer(int id) throws UnknownPlayerException {
        playersDao.deletePlayer(id);
    }
}
