package com.app.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collection;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FootballClubDto {

    private Long id;
    private String name;
    private String shortName;
    private String pictureUrl;
    private ClubStatisticsDto clubStatistics;
    private Collection<PlayerDto> players;
    private Collection<Long> matchesAsHostIds;
    private Collection<Long> matchesAsOpponentIds;
    private double power;

    public double getPower() {
        return Math.round(power);
    }
}
