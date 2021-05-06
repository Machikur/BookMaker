package com.app.service;

import com.app.domain.Player;
import com.app.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    public Set<Player> findAllByClubId(Long clubId) {
        return playerRepository.findAllByFootballClubId(clubId);
    }

    public Optional<Player> findByFullNameContainingIgnoreCase(String name) {
        return playerRepository.findFirstByFullNameContainingIgnoreCase(name);
    }


}
