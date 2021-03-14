package com.app.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillsDto {
    private Long id;
    private Integer speed;
    private Integer teamPlay;
    private Integer endurance;
}
