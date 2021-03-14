package com.app.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ClubStatistics {

    @Id
    @GeneratedValue
    private Long id;

    private Integer winners = 0;

    private Integer losers = 0;

    private Integer draws = 0;
}
