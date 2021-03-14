package com.app.dto;

import com.app.domain.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    private String fullName;
    private String footballClubName;
    private String pictureUrl;
    private Collection<Long> goalIds;
    private SkillsDto skills;
    private PlayerPosition position;


}
