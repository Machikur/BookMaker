package com.app.configuration;

import com.app.domain.ActiveUser;
import com.app.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthenticationHandler implements AuthenticationSuccessHandler {

    private final ActiveUser activeUser;

    public AuthenticationHandler(ActiveUser activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        activeUser.saveSession(user);
        request.getSession().setAttribute("cash",user.getAccount().getCash());
        response.sendRedirect("/");
    }

}
