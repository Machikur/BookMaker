package com.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne
    private Account account;

    @NotNull
    private LocalDateTime payTime;

    @NotNull
    private BigDecimal amount;

    private boolean positive;

    public Payment(Account account, @NotNull BigDecimal amount, boolean positive) {
        this.account = account;
        this.amount = amount;
        this.positive = positive;
    }

    @PrePersist
    void setPayTime() {
        this.payTime = LocalDateTime.now();
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return payTime.format(formatter);
    }


}
