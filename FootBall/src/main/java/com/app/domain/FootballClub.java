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

    @NotBlank
    private String pictureUrl;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private ClubStatistics clubStatistics;

    @OneToMany(mappedBy = "footballClub", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Player> players = new HashSet<>();

    private double power;

    @OneToMany(mappedBy = "hostTeam", cascade = CascadeType.PERSIST)
    private Set<Match> matchesAsHost = new HashSet<>();

    @OneToMany(mappedBy = "oppositeTeam", cascade = CascadeType.PERSIST)
    private Set<Match> matchesAsOpponent = new HashSet<>();

    public FootballClub(@NotBlank String name, @NotBlank String shortName, @NotBlank String pictureUrl) {
        this.name = name;
        this.shortName = shortName;
        this.pictureUrl = pictureUrl;
        this.clubStatistics = new ClubStatistics();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || id == null) return false;
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

    @PrePersist
    @PreUpdate
    void countPower() {
        power = players.stream()
                .peek(Player::countSkillsLevel)
                .mapToDouble(Player::getSkillLevel)
                .average()
                .orElse(0d);
    }

}
