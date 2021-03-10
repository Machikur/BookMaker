package com.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private FootballClub firstTeam;

    @ManyToOne(cascade = CascadeType.MERGE)
    private FootballClub oppositeTeam;

    private LocalDate dateOfMatch;

    private LocalTime startTime;

    private LocalTime finishTime;

    private Long addedTimeInSeconds;

    private Boolean finished = false;

    private Winner winner;

    @OneToMany
    private Set<Goal> goals= new HashSet<>();

    public void addGoal(Goal goal){
        goals.add(goal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match that = (Match) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
