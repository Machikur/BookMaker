package com.app.service;

import com.app.domain.Account;
import com.app.domain.User;
import com.app.exception.UserServiceException;
import com.app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User saveNewUser(User user) throws UserServiceException {
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserServiceException("Podane nazwa użytkownika już istnieje");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAccount(new Account());
        return repository.save(user);
    }

    public boolean existByUsername(String username){
       return repository.existsByUsername(username);
    }

    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono użytkownika"));
    }

    @PostConstruct
    public void saveAdmin() {
        User user = new User("Admin", "Admin");
        user.setAccount(new Account());
        updateUser(user);
    }

}
