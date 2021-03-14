package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class GoalDto {
    private Long id;
    private String playerName;
    private Long matchId;
    private LocalDate dateOfGoal;
    private LocalTime timeOfGoal;

}
