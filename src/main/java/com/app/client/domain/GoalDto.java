package com.app.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoalDto {
    private Long id;
    private String playerName;
    private Long matchId;
    private LocalDate dateOfGoal;
    private LocalTime timeOfGoal;

}
