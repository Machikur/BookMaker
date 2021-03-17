package com.app.domain;

import com.app.client.domain.Winner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
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

    private Boolean wonTicket=null;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @NotNull
    private Winner guessedWinner;

    @NotNull
    private BigDecimal quote;

    @NotNull
    private BigDecimal quoteToWin;

    private boolean done =false;

    private LocalDateTime createTime;

    public Ticket(@NotNull Long matchId, @NotBlank String hostTeam, @NotBlank String opTeam) {
        this.matchId = matchId;
        this.hostTeam = hostTeam;
        this.opTeam = opTeam;
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createTime.format(formatter);
    }

    @PrePersist
    void setCreateTime(){
        createTime=LocalDateTime.now();
    }

}
