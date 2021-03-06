package com.app.configuration.match;

import com.app.domain.FootballClub;
import com.app.domain.Goal;
import com.app.domain.Match;
import com.app.domain.Player;
import com.app.service.FootballClubService;
import com.app.service.GoalService;
import com.app.service.MatchService;
import com.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RandomMatchManager implements MatchManager {

    private final static int MAX_SECONDS_BETWEEN_GOALS = 1800;
    private final static int CHANCE_TO_GOAL_IN_PERCENTAGES = 50;
    private final static String OPPOSITE_TEAM = "opTeam";
    private final static String FIRST_TEAM = "firstTeam";
    private final MatchService matchService;
    private final PlayerService playerService;
    private final FootballClubService footballClubService;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final GoalService goalService;
    private LocalDateTime currentFakeMatchTime;

    public synchronized Match doMatch(Long matchId) {
        Match match = matchService.findById(matchId).get();
        currentFakeMatchTime = LocalDateTime.of(match.getDateOfMatch(), match.getStartTime());

        FootballClub firstTeam = footballClubService.findById(match.getHostTeam().getId()).get();
        FootballClub opTeam = footballClubService.findById(match.getOppositeTeam().getId()).get();
        LocalDateTime finishTime = LocalDateTime.of(match.getDateOfMatch(), match.getFinishTime());

        while (addRandomTimeAndCheckIfIsNotBoundOfTime(finishTime)) {
            if (shouldShotGoal()) {
                switch (decideWhoShouldShotGoal(firstTeam, opTeam)) {
                    case OPPOSITE_TEAM:
                        createAndSaveRandomGoal(match, opTeam, currentFakeMatchTime);
                        break;
                    case FIRST_TEAM:
                        createAndSaveRandomGoal(match, firstTeam, currentFakeMatchTime);
                        break;
                }
            }
        }
        matchService.setWinnerAndSave(match);
        log.info("mecz pomi??dzy {} i {} zosta?? zako??czony", match.getHostTeam(), match.getOppositeTeam());
        return match;
    }

    private String decideWhoShouldShotGoal(FootballClub host, FootballClub opTeam) {
        double randomDouble = random.nextInt((int) (host.getPower() + opTeam.getPower()) + 1);
        if (randomDouble > host.getPower()) {
            return OPPOSITE_TEAM;
        } else
            return FIRST_TEAM;
    }

    private boolean addRandomTimeAndCheckIfIsNotBoundOfTime(LocalDateTime finishTime) {
        this.currentFakeMatchTime = currentFakeMatchTime.plusSeconds(random.nextInt(MAX_SECONDS_BETWEEN_GOALS));
        return finishTime.isAfter(currentFakeMatchTime);
    }

    private boolean shouldShotGoal() {
        return CHANCE_TO_GOAL_IN_PERCENTAGES > random.nextInt(100);
    }

    private void createAndSaveRandomGoal(Match match, FootballClub club, LocalDateTime time) {
        Player shooter = chosePlayerToGoal(club.getPlayers());
        Goal goal = new Goal(time.toLocalDate(), time.toLocalTime());
        goal = goalService.saveGoal(goal);
        shooter.addGoal(goal);
        playerService.savePlayer(shooter);
        match.addGoal(goal);
        matchService.saveMatch(match);
    }

    private Player chosePlayerToGoal(Set<Player> players) {
        List<Player> shuffled = new ArrayList<>(players);
        Collections.shuffle(shuffled);
        Player shooter = null;
        while (shooter == null) {
            for (Player sortedPlayer : shuffled) {
                if (decideIfShouldShotGoal(sortedPlayer)) {
                    shooter = playerService.findById(sortedPlayer.getId()).get();
                    break;
                }
            }
        }
        return shooter;
    }

    private boolean decideIfShouldShotGoal(Player player) {
        int downBorder = random.nextInt(150) + 50;
        return player.getSkillLevel() > downBorder;
    }

}
