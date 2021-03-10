package com.app.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@NoArgsConstructor
public class Skills {

    @Id
    @GeneratedValue
    private Long id;

    @Max(100)
    @Min(0)
    private Integer speed;

    @Max(100)
    @Min(0)
    private Integer teamPlay;

    @Max(100)
    @Min(0)
    private Integer endurance;

    public Skills(@Max(100) @Min(0) Integer speed, @Max(100) @Min(0) Integer teamPlay, @Max(100) @Min(0) Integer endurance) {
        this.speed = speed;
        this.teamPlay = teamPlay;
        this.endurance = endurance;
    }

    double countLevel() {
        return (speed * 2 + teamPlay * 1.5) / (100f / endurance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skills skills = (Skills) o;
        return id.equals(skills.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
