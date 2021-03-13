package com.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class FootballClub implements HasId {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String shortName;

    @OneToMany(mappedBy = "footballClub", cascade = {CascadeType.PERSIST})
    private Set<Player> players = new HashSet<>();

    @OneToMany(mappedBy = "hostTeam", cascade = CascadeType.PERSIST)
    private Set<Match> matchesAsHost = new HashSet<>();

    @OneToMany(mappedBy = "oppositeTeam", cascade = CascadeType.PERSIST)
    private Set<Match> matchesAsOpponent = new HashSet<>();

    public FootballClub(@NotBlank String name, @NotBlank String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub that = (FootballClub) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return name + " (" + shortName + ")";
    }
}
