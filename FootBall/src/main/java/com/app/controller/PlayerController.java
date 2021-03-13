package com.app.controller;

import com.app.domain.Player;
import com.app.dto.PlayerDto;
import com.app.mapper.AppMapper;
import com.app.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Long id) {
        Optional<Player> optionalPlayer = playerService.findById(id);
        return optionalPlayer.map(player -> ResponseEntity.ok(AppMapper.mapToDto(player)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/club/{id}")
    public ResponseEntity<Collection<PlayerDto>> getPlayersByFootballClubId(@PathVariable Long id) {
        Set<Player> players = playerService.findAllByClubId(id);
        Collection<PlayerDto> playerDtos=AppMapper.mapToPlayerListDto(players);
        if (players.size() != 0) {
            return ResponseEntity.ok(playerDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
