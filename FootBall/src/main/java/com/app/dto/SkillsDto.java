package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkillsDto {
    private Long id;
    private Integer speed;
    private Integer teamPlay;
    private Integer endurance;
}
