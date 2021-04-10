package com.app.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
