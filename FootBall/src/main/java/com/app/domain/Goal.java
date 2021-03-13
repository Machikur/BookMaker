package com.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @JoinColumn
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.PERSIST})
    private Player player;

    @NotNull
    @JoinColumn
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.PERSIST})
    private Match match;

    @NotNull
    private LocalDate dateOfGoal;

    @NotNull
    private LocalTime timeOfGoal;

    public Goal(@NotNull LocalDate dateOfGoal, @NotNull LocalTime timeOfGoal) {
        this.dateOfGoal = dateOfGoal;
        this.timeOfGoal = timeOfGoal;
    }

    public void setMatch(Match match){
        this.match=match;
        match.addGoal(this);
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

    @Override
    public String toString() {
        return "Gol strzelony przez " + player.toString() + "\n" +
                "Dnia " + dateOfGoal + "\n" +
                "O godzienie " + timeOfGoal + "\n";
    }
}
