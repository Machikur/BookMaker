package com.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Player implements HasId {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String fullName;

    @ManyToOne
    private FootballClub footballClub;

    private String pictureUrl = "https://icon-library.com/images/anonymous-icon/anonymous-icon-0.jpg";

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.MERGE)
    private Set<Goal> goals = new HashSet<>();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Skills skills;

    private double skillLevel;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    public Player(@NotBlank String fullName, @NotNull Skills skills, @NotNull PlayerPosition position) {
        this.fullName = fullName;
        this.skills = skills;
        this.position = position;
    }

    public Player(@NotBlank String fullName, @NotNull Skills skills, @NotNull PlayerPosition position, String pictureUrl) {
        this.fullName = fullName;
        this.pictureUrl = pictureUrl;
        this.skills = skills;
        this.position = position;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
        goal.setPlayer(this);
    }

    public void setFootballClub(FootballClub footballClub) {
        this.footballClub = footballClub;
        footballClub.getPlayers().add(this);
    }

    @PreUpdate
    @PrePersist
    void countSkillsLevel() {
        skillLevel = skills.countLevel() * position.getChanceInPercentagesToShotGoal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || id == null) return false;
        Player that = (Player) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return fullName;
    }
}
