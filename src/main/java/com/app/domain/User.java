package com.app.domain;

import com.app.client.exception.NotEnoughCashException;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private boolean enabled = true;

    private BigDecimal cash;

    @OneToMany(mappedBy = "user")
    private Set<Ticket> tickets = new HashSet<>();


    public void putCash(BigDecimal amount) {
        cash = cash.add(amount);
    }

    public void pay(BigDecimal amount) throws NotEnoughCashException {
        if (cash.compareTo(amount) > 0) {
            cash = cash.subtract(amount);
        } else {
            throw new NotEnoughCashException();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> "USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

}
