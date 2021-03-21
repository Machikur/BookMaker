package com.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationHandler authenticationHandler;
    private final PasswordEncoder encoder;

    public WebSecurityConfiguration(UserDetailsService userDetailsService, AuthenticationHandler authenticationHandler, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.authenticationHandler = authenticationHandler;
        this.encoder = encoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

}
