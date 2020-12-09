package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.dao.repository.MatchStatsRepository;
import hu.unideb.webdev.dao.repository.MatchesRepository;
import hu.unideb.webdev.dao.repository.PlayersRepository;
import hu.unideb.webdev.dao.repository.TeamRepository;
import hu.unideb.webdev.exceptions.*;
import hu.unideb.webdev.model.MatchStats;
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
public class MatchStatsDaoImpl implements MatchStatsDao{

    private final MatchStatsRepository matchStatsRepository;
    private final MatchesRepository matchesRepository;
    private final TeamRepository teamRepository;
    private final PlayersRepository playersRepository;

    @Override
    public void createMatchStat(MatchStats matchStats) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException, ExistingMatchStatException {
        MatchStatsEntity matchStatsEntity;
        matchStatsEntity= MatchStatsEntity.builder()
                .id(createId(matchStats.getMid(),matchStats.getPid()))
                .team(queryTeam(matchStats.getTid()))
                .location(matchStats.getLoc())
                .kicks(matchStats.getKI())
                .marks(matchStats.getMK())
                .handballs(matchStats.getHB())
                .disposals(matchStats.getDI())
                .goals(matchStats.getGL())
                .behinds(matchStats.getBH())
                .hitOuts(matchStats.getHO())
                .tackles(matchStats.getTK())
                .rebound50s(matchStats.getRB())
                .inside50s(matchStats.getIF())
                .clearances(matchStats.getCL())
                .clangers(matchStats.getCG())
                .freeKicksFor(matchStats.getFF())
                .freeKicksAgainst(matchStats.getFA())
                .brownlowVotes(matchStats.getBR())
                .contestedPossessions(matchStats.getCP())
                .uncontestedPossessions(matchStats.getUP())
                .contestedMarks(matchStats.getCM())
                .marksInside50(matchStats.getMI())
                .onePercenters(matchStats.getOneP())
                .bounces(matchStats.getBO())
                .goalAssist(matchStats.getGA())
                .percentageOfGamePlayed(matchStats.getPP())
                .build();

        log.info("MatchStatsEntity: {}",matchStatsEntity);

        try {
            matchStatsRepository.save(matchStatsEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
    protected MatchStatsId createId(String mid, int pid) throws UnknownMatchException, UnknownPlayerException, ExistingMatchStatException {
        MatchStatsId id = new MatchStatsId(queryMatch(mid),queryPlayer(pid));
        Optional<MatchStatsEntity> matchStatsEntity = matchStatsRepository.findById(id);

        if (matchStatsEntity.isPresent()){
            throw new ExistingMatchStatException(String.format("MatchStat Found %s",matchStatsEntity));
        }

        return id;
    }

    protected MatchesEntity queryMatch(String mid) throws UnknownMatchException{
        Optional<MatchesEntity> matchesEntity = matchesRepository.findById(mid);

        if (matchesEntity.isEmpty()){
            throw new UnknownMatchException(String.format("Match Not Found ID:%s",mid));
        }

        return matchesEntity.get();

    }
    protected PlayersEntity queryPlayer(int pid) throws UnknownPlayerException{
        Optional<PlayersEntity> playersEntity = playersRepository.findById(pid);

        if (playersEntity.isEmpty()){
            throw new UnknownPlayerException(String.format("Player Not Found ID:%d",pid));
        }

        return playersEntity.get();

    }
    protected TeamsEntity queryTeam(int tid) throws UnknownTeamException{
        Optional<TeamsEntity> teamsEntity = teamRepository.findById(tid);

        if (teamsEntity.isEmpty()){
            throw new UnknownTeamException(String.format("Team Not Found ID:%d",tid));
        }

        return teamsEntity.get();

    }

    @Override
    public Collection<MatchStats> readAll() {
        log.info("Listing all match stats");

        return StreamSupport.stream(matchStatsRepository.findAll().spliterator(),false)
                .map(entity -> new MatchStats(
                        entity.getId().getMid().getId(),
                        entity.getId().getPid().getId(),
                        entity.getTeam().getId(),
                        entity.getLocation(),
                        entity.getKicks(),
                        entity.getMarks(),
                        entity.getHandballs(),
                        entity.getDisposals(),
                        entity.getGoals(),
                        entity.getBehinds(),
                        entity.getHitOuts(),
                        entity.getTackles(),
                        entity.getRebound50s(),
                        entity.getInside50s(),
                        entity.getClearances(),
                        entity.getClangers(),
                        entity.getFreeKicksFor(),
                        entity.getFreeKicksAgainst(),
                        entity.getBrownlowVotes(),
                        entity.getContestedPossessions(),
                        entity.getUncontestedPossessions(),
                        entity.getContestedMarks(),
                        entity.getMarksInside50(),
                        entity.getOnePercenters(),
                        entity.getBounces(),
                        entity.getGoalAssist(),
                        entity.getPercentageOfGamePlayed()
                ))
                .collect(Collectors.toList());

    }

    @Override
    public void updateMatchStat(MatchStats matchStats) throws UnknownMatchStatException, UnknownTeamException, UnknownMatchException, UnknownPlayerException {
        Optional<MatchStatsEntity> matchStatsEntity = matchStatsRepository.findById(new MatchStatsId(queryMatch(matchStats.getMid()),queryPlayer(matchStats.getPid())));
        if (matchStatsEntity.isEmpty()){
            throw new UnknownMatchStatException(String.format("MatchStat Not Found %s",matchStats));
        }
        matchStatsEntity.get().setTeam(queryTeam(matchStats.getTid()));
        matchStatsEntity.get().setLocation(matchStats.getLoc());
        matchStatsEntity.get().setKicks(matchStats.getKI());
        matchStatsEntity.get().setMarks(matchStats.getMK());
        matchStatsEntity.get().setHandballs(matchStats.getHB());
        matchStatsEntity.get().setDisposals(matchStats.getDI());
        matchStatsEntity.get().setGoals(matchStats.getGL());
        matchStatsEntity.get().setBehinds(matchStats.getBH());
        matchStatsEntity.get().setHitOuts(matchStats.getHO());
        matchStatsEntity.get().setTackles(matchStats.getTK());
        matchStatsEntity.get().setRebound50s(matchStats.getRB());
        matchStatsEntity.get().setInside50s(matchStats.getIF());
        matchStatsEntity.get().setClearances(matchStats.getCL());
        matchStatsEntity.get().setClangers(matchStats.getCG());
        matchStatsEntity.get().setFreeKicksFor(matchStats.getFF());
        matchStatsEntity.get().setFreeKicksAgainst(matchStats.getFA());
        matchStatsEntity.get().setBrownlowVotes(matchStats.getBR());
        matchStatsEntity.get().setContestedPossessions(matchStats.getCP());
        matchStatsEntity.get().setUncontestedPossessions(matchStats.getUP());
        matchStatsEntity.get().setContestedMarks(matchStats.getCM());
        matchStatsEntity.get().setMarksInside50(matchStats.getMI());
        matchStatsEntity.get().setOnePercenters(matchStats.getOneP());
        matchStatsEntity.get().setBounces(matchStats.getBO());
        matchStatsEntity.get().setGoalAssist(matchStats.getGA());
        matchStatsEntity.get().setPercentageOfGamePlayed(matchStats.getPP());

        try {
            matchStatsRepository.save(matchStatsEntity.get());
        } catch (Exception e){
            log.error(e.getMessage());
        }

    }

    @Override
    public void deleteMatchStat(String mid, int pid) throws UnknownMatchStatException, UnknownMatchException, UnknownPlayerException {
        MatchStatsId id = new MatchStatsId(queryMatch(mid), queryPlayer(pid));
        Optional<MatchStatsEntity> matchStatsEntity = matchStatsRepository.findById(id);
        if (matchStatsEntity.isEmpty()){
            throw new UnknownMatchStatException(String.format("MatchStat with this ID Not Found %s",id.getMid().getId()+""+id.getPid().getId()));
        }

        matchStatsRepository.delete(matchStatsEntity.get());

    }
}
