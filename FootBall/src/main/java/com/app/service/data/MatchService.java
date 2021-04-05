package com.app.service.data;

import com.app.domain.*;
import com.app.repository.MatchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final StatisticsService statisticsService;
    private final FootballClubService footballClubService;

    public MatchService(MatchRepository matchRepository, StatisticsService statisticsService, FootballClubService footballClubService) {
        this.matchRepository = matchRepository;
        this.statisticsService = statisticsService;
        this.footballClubService = footballClubService;
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public Set<Match> findAllMatchesOfFootballClubId(Long footballClubId) {
        return matchRepository.findAllByOppositeTeamIdOrHostTeamId(footballClubId, footballClubId);
    }

    public Set<Match> findAllByDateOfMatch(LocalDate dateOfMatch) {
        return matchRepository.findAllByDateOfMatch(dateOfMatch);
    }

    public Set<Match> findAllByFinished(boolean finished) {
        return matchRepository.findAllByFinished(finished);
    }

    public Page<Match> findAllByFinished(boolean finished, Pageable pageable) {
        return matchRepository.findAllByFinishedOrderByDateOfMatchDesc(finished,pageable);
    }

    public Optional<Match> findById(Long id) {
        return matchRepository.findById(id);
    }

    public int countAllByFinished(boolean finished) {
        return matchRepository.countAllByFinished(finished);
    }

    @Transactional
    public synchronized void setWinnerAndSave(Match match) {
        FootballClub host = footballClubService.findById(match.getHostTeam().getId()).get();
        FootballClub opponent = footballClubService.findById(match.getOppositeTeam().getId()).get();
        Set<Player> hostPlayers = host.getPlayers();
        Set<Player> opTeamPlayers = opponent.getPlayers();
        Set<Goal> goals = match.getGoals();

        long hostTeamGoals = goals.stream()
                .map(Goal::getPlayer)
                .filter(player -> hostPlayers.contains(player))
                .count();

        long opTeamGoals = goals.stream()
                .map(Goal::getPlayer)
                .filter(player -> opTeamPlayers.contains(player))
                .count();

        if (opTeamGoals == hostTeamGoals) {
            match.setWinner(Winner.DRAW);
            statisticsService.addPointAndSave(Point.DRAW, host.getClubStatistics());
            statisticsService.addPointAndSave(Point.DRAW, opponent.getClubStatistics());
        } else if (opTeamGoals > hostTeamGoals) {
            match.setWinner(Winner.OPPOSITE_TEAM);
            statisticsService.addPointAndSave(Point.DOWN, host.getClubStatistics());
            statisticsService.addPointAndSave(Point.UP, opponent.getClubStatistics());
        } else {
            match.setWinner(Winner.HOST_TEAM);
            statisticsService.addPointAndSave(Point.UP, host.getClubStatistics());
            statisticsService.addPointAndSave(Point.DOWN, opponent.getClubStatistics());
        }
        match.setResult(hostTeamGoals + " : " + opTeamGoals);
        match.setFinished(true);
        saveMatch(match);
    }

}
