package com.app.dto;

import com.app.domain.Winner;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Data
@AllArgsConstructor
public class MatchDto {

    private Long id;
    private FootballClubDto hostTeam;
    private FootballClubDto oppositeTeam;
    private LocalDate dateOfMatch;
    private LocalTime startTime;
    private LocalTime finishTime;
    private Boolean finished;
    private Winner winner;
    private Collection<GoalDto> goals;
    private String result;

}
