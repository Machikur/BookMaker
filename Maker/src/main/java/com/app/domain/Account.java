package com.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @DecimalMin("0")
    private BigDecimal cash;

    @OneToMany(mappedBy = "account",
            cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @PrePersist
    void cashForStart() {
        this.cash = BigDecimal.valueOf(100);
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public void addCash(BigDecimal extraCash) {
        this.cash = this.cash.add(extraCash);
    }
}
