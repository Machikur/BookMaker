package com.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Match {

    private final static int MATCH_TIME_IN_MINUTES = 90;

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne(cascade = {CascadeType.MERGE})
    private FootballClub hostTeam;

    @JoinColumn
    @ManyToOne(cascade = {CascadeType.MERGE})
    private FootballClub oppositeTeam;

    private LocalDate dateOfMatch;

    private LocalTime startTime;

    private LocalTime finishTime;

    private Boolean finished = false;

    private Winner winner;

    @OneToMany(mappedBy = "match")
    private Set<Goal> goals = new HashSet<>();

    public Match(FootballClub hostTeam, FootballClub oppositeTeam, LocalDate dateOfMatch, LocalTime startTime) {
        this.hostTeam = hostTeam;
        this.oppositeTeam = oppositeTeam;
        this.dateOfMatch = dateOfMatch;
        this.startTime = startTime;
        setFinishTime();
    }

    private void setFinishTime() {
        this.finishTime = this.startTime.plusMinutes(MATCH_TIME_IN_MINUTES);
    }

    public void addGoal(Goal goal) {
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

    @Override
    public String toString() {
        return "Mecz pomiędzy " + getHostTeam() + " i " + getOppositeTeam() + "\n" +
                "Dnia - " + getDateOfMatch() + "\n" +
                "Zwycięzcą został " + winner + "\n" +
                "Z wynikiem " + getResultOfMatch() + "\n" +
                "Strzelone gole : " + goalsToString()+ "\n" +
                "Mecz zakończony o godzienie - " + finishTime;
    }

    public String goalsToString() {
        return goals.size() != 0 ? goals.toString() : "brak";
    }

    public String getResultOfMatch() {
        return countGoalsByClub(hostTeam) + ":" + countGoalsByClub(oppositeTeam);
    }

    public long countGoalsByClub(FootballClub club) {
        return goals.stream()
                .filter(goal -> club.getPlayers().contains(goal.getPlayer()))
                .count();
    }

}
