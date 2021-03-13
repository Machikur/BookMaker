package com.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class FootballClubDto {

    private Long id;
    private String name;
    private String shortName;
    private Collection<PlayerDto> players;
    private Collection<Long> matchesAsHostIds;
    private Collection<Long> matchesAsOpponentIds;

}
