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
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String fullName;

    @ManyToOne
    private FootballClub footballClub;

    @OneToMany(mappedBy = "player")
    private Set<Goal> goals= new HashSet<>();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Skills skills;

    @NotNull
    private PlayerPosition position;

    public Player(@NotBlank String fullName, @NotNull Skills skills, @NotNull PlayerPosition position) {
        this.fullName = fullName;
        this.skills = skills;
        this.position = position;
    }

    public void addGoal(Goal goal){
        goals.add(goal);
    }

    public double countSkillsLevel() {
        return skills.countLevel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
