package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.PlayersEntity;
import hu.unideb.webdev.dao.repository.PlayersRepository;
import hu.unideb.webdev.exceptions.ExistingPlayerException;
import hu.unideb.webdev.exceptions.UnknownPlayerException;
import hu.unideb.webdev.model.Players;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayersDaoImpl implements PlayersDao {

    private final PlayersRepository playersRepository;

    @Override
    public void createPlayer(Players player) throws ExistingPlayerException {
        PlayersEntity playersEntity;

        checkPlayer(player.getId());

        playersEntity=PlayersEntity.builder()
        .id(player.getId())
        .dateOfBirth(player.getDob())
        .firstName(player.getFirstName())
        .lastName(player.getLastName())
        .height(player.getHeight())
        .weight(player.getWeight())
        .build();

        log.info("PlayersEntity: {}",playersEntity);
        try {
            playersRepository.save(playersEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    protected void checkPlayer(int id) throws ExistingPlayerException{
        Optional<PlayersEntity> playersEntity = playersRepository.findById(id);

        if (playersEntity.isPresent()){
            throw new ExistingPlayerException(String.format("Player Found with ID: %d",id));
        }

    }

    @Override
    public Collection<Players> readAll() {
        log.info("Listing all players");
        return StreamSupport.stream(playersRepository.findAll().spliterator(),false)
                .map(entity -> new Players(
                        entity.getId(),
                        entity.getDateOfBirth(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getHeight(),
                        entity.getWeight()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void updatePlayer(Players player) throws UnknownPlayerException {
        Optional<PlayersEntity> playersEntity = playersRepository.findById(player.getId());
        if (playersEntity.isEmpty()){
            throw new UnknownPlayerException(String.format("Player Not Found %s",player));
        }
        playersEntity.get().setDateOfBirth(player.getDob());
        playersEntity.get().setFirstName(player.getFirstName());
        playersEntity.get().setLastName(player.getLastName());
        playersEntity.get().setHeight(player.getHeight());
        playersEntity.get().setWeight(player.getWeight());

        try {
            playersRepository.save(playersEntity.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deletePlayer(int id) throws UnknownPlayerException {
        Optional<PlayersEntity> playersEntity = playersRepository.findById(id);
        if (playersEntity.isEmpty()){
            throw new UnknownPlayerException(String.format("Player with this ID Not Found %s",id));
        }
        playersRepository.delete(playersEntity.get());
    }
}
