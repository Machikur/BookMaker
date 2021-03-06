package com.app.mapper;

import com.app.domain.*;
import com.app.dto.*;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

public class AppMapper {

    public static MatchDto mapToDto(Match match) {
        return new MatchDto(match.getId(),
                mapToDto(match.getHostTeam()),
                mapToDto(match.getOppositeTeam()),
                match.getDateOfMatch(),
                match.getStartTime(),
                match.getFinishTime(),
                match.getFinished(),
                match.getWinner(),
                mapToGoalDtoCollection(match.getGoals()),
                match.getResult());
    }

    public static FootballClubDto mapToDto(FootballClub footballClub) {
        return new FootballClubDto(
                footballClub.getId(),
                footballClub.getName(),
                footballClub.getShortName(),
                footballClub.getPictureUrl(),
                mapToDto(footballClub.getClubStatistics()),
                mapToPlayerListDto(footballClub.getPlayers()),
                mapToIdList(footballClub.getMatchesAsHost()),
                mapToIdList(footballClub.getMatchesAsOpponent()),
                footballClub.getPower());
    }

    public static GoalDto mapToDto(Goal goal) {
        return new GoalDto(goal.getId(), goal.getPlayer().getFullName(), goal.getMatch().getId(), goal.getDateOfGoal(), goal.getTimeOfGoal());
    }

    public static PlayerDto mapToDto(Player player) {
        return new PlayerDto(
                player.getId(),
                player.getFullName(),
                player.getFootballClub().getName(),
                player.getPictureUrl(),
                mapToIdList(player.getGoals()),
                mapSkillsToDto(player.getSkills()),
                player.getPosition());
    }

    public static Collection<PlayerDto> mapToPlayerListDto(Collection<Player> collection) {
        return collection.stream()
                .map(AppMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static Collection<GoalDto> mapToGoalListDto(Collection<Goal> collection) {
        return collection.stream()
                .map(AppMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static ClubStatisticsDto mapToDto(ClubStatistics statistics) {
        return new ClubStatisticsDto(statistics.getId(), statistics.getWinners(), statistics.getLosers(), statistics.getDraws());
    }

    public static Page<MatchDto> mapToMatchDtoPage(Page<Match> matchPage) {
        return matchPage.map(AppMapper::mapToDto);
    }

    public static Collection<MatchDto> mapToMatchListDto(Collection<Match> collection) {
        return collection.stream()
                .map(AppMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static Collection<FootballClubDto> mapToClubListDto(Collection<FootballClub> collection) {
        return collection.stream()
                .map(AppMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private static SkillsDto mapSkillsToDto(Skills skills) {
        return new SkillsDto(skills.getId(), skills.getSpeed(), skills.getTeamPlay(), skills.getEndurance());
    }

    private static Collection<GoalDto> mapToGoalDtoCollection(Collection<Goal> goals) {
        return goals.stream().map(AppMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private static Collection<Long> mapToIdList(Collection<? extends HasId> collection) {
        return collection.stream()
                .map(HasId::getId)
                .collect(Collectors.toList());
    }

}
