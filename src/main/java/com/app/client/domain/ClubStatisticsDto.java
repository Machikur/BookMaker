package com.app.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubStatisticsDto {
    private Long id;
    private int winners;
    private int losers;
    private int draws;

    public int getTotal() {
        return winners + losers + draws;
    }
}
