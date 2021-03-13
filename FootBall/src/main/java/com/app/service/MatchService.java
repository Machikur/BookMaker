package com.app.service;

import com.app.domain.Goal;
import com.app.domain.Match;
import com.app.domain.Player;
import com.app.domain.Winner;
import com.app.exception.WinnerException;
import com.app.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }


    public Set<Match> findAllMatchesOfFootballClub(Long footballClubId) {
        return matchRepository.findAllByOppositeTeamIdOrHostTeamId(footballClubId, footballClubId);
    }

    public Set<Match> findAllByDateOfMatch(LocalDate dateOfMatch) {
        return matchRepository.findAllByDateOfMatch(dateOfMatch);
    }

    public Set<Match> findAllByFinished(boolean finished) {
        return matchRepository.findAllByFinished(finished);
    }

    public Optional<Match> findById(Long id) {
        return matchRepository.findById(id);
    }

    protected Match setWinner(Match match) {
        if (match.getFinishTime() != null) {
            Set<Player> firstTeamPlayers = match.getHostTeam().getPlayers();
            Set<Player> opTeamPlayers = match.getOppositeTeam().getPlayers();
            Set<Goal> goals = match.getGoals();

            long firstTeamGoals = goals.stream()
                    .map(Goal::getPlayer)
                    .filter(player -> firstTeamPlayers.contains(player))
                    .count();

            long opTeamGoals = goals.stream()
                    .map(Goal::getPlayer)
                    .filter(player -> opTeamPlayers.contains(player))
                    .count();

            if (opTeamGoals == firstTeamGoals) {
                match.setWinner(Winner.DRAW);
            } else if (opTeamGoals > firstTeamGoals) {
                match.setWinner(Winner.OPPOSITE_TEAM);
            } else {
                match.setWinner(Winner.HOST_TEAM);
            }
            return match;
        }
        throw new WinnerException("Mecz jeszcze sie nie skończył");
    }

}
