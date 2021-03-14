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
@Table(name = "Matches")
public class Match implements HasId {

    private final static int MATCH_TIME_IN_MINUTES = 90;

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne
    private FootballClub hostTeam;

    @JoinColumn
    @ManyToOne
    private FootballClub oppositeTeam;

    private LocalDate dateOfMatch;

    private LocalTime startTime;

    private LocalTime finishTime;

    private Boolean finished = false;

    private String result;

    @Enumerated(EnumType.STRING)
    private Winner winner;

    @OneToMany(mappedBy = "match", cascade = CascadeType.MERGE)
    private Set<Goal> goals = new HashSet<>();

    public Match(FootballClub hostTeam, FootballClub oppositeTeam, LocalDate dateOfMatch, LocalTime startTime) {
        this.hostTeam = hostTeam;
        hostTeam.getMatchesAsHost().add(this);
        this.oppositeTeam = oppositeTeam;
        oppositeTeam.getMatchesAsOpponent().add(this);
        this.dateOfMatch = dateOfMatch;
        this.startTime = startTime;
        setFinishTime();
    }

    private void setFinishTime() {
        this.finishTime = this.startTime.plusMinutes(MATCH_TIME_IN_MINUTES);
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
        goal.setMatch(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || id == null) return false;
        Match that = (Match) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return !finished ? matchToString() : matchToString() + "\n " + resultToString();

    }

    public String matchToString() {
        return "Mecz pomiędzy " + getHostTeam() + " i " + getOppositeTeam() + "\n" +
                "Czas rozpoczęcia - " + getDateOfMatch() + " " + getStartTime();
    }

    private String resultToString() {
        return "Zwycięzcą został " + winner + "\n" +
                "Z wynikiem " + getResultOfMatch() + "\n" +
                "Strzelone gole : " + goalsToString() + "\n" +
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
