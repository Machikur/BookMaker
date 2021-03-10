package com.app.service;

import com.app.domain.FootballClub;
import com.app.domain.Goal;
import com.app.domain.Match;
import com.app.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Engine {

    private final MatchService matchService;
    private final PlayerService playerService;
    private final GoalService goalService;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final static int MAX_TIME_BETWEEN_GOAL = 45;

    public synchronized void doMatch(Long matchId) throws InterruptedException {
        Match match = matchService.findById(matchId).get();
        setRandomExtraTimeAndSave(match);

        FootballClub firstTeam = match.getFirstTeam();
        FootballClub opTeam = match.getOppositeTeam();

        double powerOfFirstTeam = countFootballClubPower(firstTeam);
        double powerOfOpTeam = countFootballClubPower(opTeam);

        for (;;) {
            long randomMinutes = random.nextInt(MAX_TIME_BETWEEN_GOAL);
            if (checkIfIsNotBoundOfTime(match, randomMinutes)) {
                if (randomGoalChanceGenerator()) {


                    Thread.sleep(randomMinutes * 60000);
                }
            } else {
                break;
            }
        }


    }

    private void setRandomExtraTimeAndSave(Match match) {
        long extraSeconds = random.nextInt(6000) / 60;
        match.setAddedTimeInSeconds(extraSeconds);
        matchService.saveMatch(match);
    }

    private boolean checkIfIsNotBoundOfTime(Match match, long seconds) {
        LocalDateTime timeOfFinish = LocalDateTime.of(match.getDateOfMatch(), match.getFinishTime());
        return LocalDateTime.now().isAfter(timeOfFinish.plusMinutes(seconds));
    }

    private boolean randomGoalChanceGenerator() {
        return random.nextInt(100) % 9 == 0;
    }

    private void createAndSaveRandomGoal(Match match, FootballClub club) {
        Player shooter = chosePlayerToGoal(club.getPlayers());
        Goal goal = new Goal(shooter, match, LocalDate.now(), LocalTime.now());
        goalService.SaveGoal(goal);
        match.addGoal(goal);
        matchService.saveMatch(match);
        shooter.addGoal(goal);
        playerService.savePlayer(shooter);
    }

    private Player chosePlayerToGoal(Set<Player> players) {
        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparingDouble(Player::countSkillsLevel))
                .collect(Collectors.toList());
        Player shooter = null;
        while (shooter == null) {
            for (int i = 0; i < sortedPlayers.size(); i++) {
                if (decideIfShouldShotGoal(sortedPlayers.get(i))) {
                    shooter = sortedPlayers.get(i);
                    break;
                }
            }
        }
        return shooter;
    }

    private boolean decideIfShouldShotGoal(Player player) {
        int downBorder = random.nextInt(150) + 50;
        return player.countSkillsLevel() > downBorder;
    }

    private double countFootballClubPower(FootballClub footballClub) {
        Collection<Player> players = footballClub.getPlayers();
        int size = players.size();
        return players.stream()
                .map(Player::countSkillsLevel)
                .reduce(Double::sum)
                .orElse(0d) / size;
    }

}
