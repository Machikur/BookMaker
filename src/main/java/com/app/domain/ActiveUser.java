package com.app.domain;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class ActiveUser {

    private Long userId;

    private String userName;

    private Account account;

    public void saveSession(User user) {
        this.userId = user.getId();
        this.userName = user.getUsername();
        this.account = user.getAccount();
    }

}
