package com.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Player player;

    @NotNull
    @ManyToOne
    private Match match;

    @NotNull
    private LocalDate dateOfGoal;

    @NotNull
    private LocalTime timeOfGoal;

    public Goal(@NotNull Player player, @NotNull Match match, @NotNull LocalDate dateOfGoal, @NotNull LocalTime timeOfGoal) {
        this.player = player;
        this.match = match;
        this.dateOfGoal = dateOfGoal;
        this.timeOfGoal = timeOfGoal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal that = (Goal) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
