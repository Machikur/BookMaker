package com.app.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collection;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDto {

    private Long id;
    private String fullName;
    private String footballClubName;
    private String pictureUrl;
    private Collection<Long> goalIds;
    private SkillsDto skills;
    private PlayerPosition position;
}
