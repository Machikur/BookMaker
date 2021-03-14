package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClubStatisticsDto {
    private Long id;
    private int winners;
    private int losers;
    private int draws;
}
