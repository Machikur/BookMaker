package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class FootballClubDto {

    private Long id;
    private String name;
    private String shortName;
    private String pictureUrl;
    private ClubStatisticsDto clubStatistics;
    private Collection<PlayerDto> players;
    private Collection<Long> matchesAsHostIds;
    private Collection<Long> matchesAsOpponentIds;

}
