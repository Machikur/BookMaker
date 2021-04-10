package com.app.domain;

import com.app.client.domain.Winner;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long matchId;

    @NotBlank
    private String hostTeam;

    @NotBlank
    private String opTeam;

    private Boolean wonTicket = null;

    @NotNull
    private Long userId;

    @NotNull
    private Winner guessedWinner;

    @NotNull
    private BigDecimal quote;

    @NotNull
    private BigDecimal quoteToWin;

    private boolean done = false;

    private LocalDate matchDate;

    private LocalTime matchTime;

    private String result = null;

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.of(matchDate, matchTime).format(formatter);
    }

}
