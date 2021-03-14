package com.app.client.service;

import com.app.client.domain.PlayerDto;
import com.app.client.urlbuilder.PlayersUrls;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayersService {

    private final RestTemplate restTemplate;
    private final PlayersUrls playersUrls;

    public PlayersService(RestTemplate restTemplate, PlayersUrls playersUrls) {
        this.restTemplate = restTemplate;
        this.playersUrls = playersUrls;
    }

    public List<PlayerDto> getPlayersByClubId(Long id) {
        ResponseEntity<PlayerDto[]> players = restTemplate.getForEntity(playersUrls.getAllPlayerByClubId(id), PlayerDto[].class);
        return Arrays.asList(players.getBody());
    }

    public PlayerDto getPlayerById(Long id) {
        ResponseEntity<PlayerDto> players = restTemplate.getForEntity(playersUrls.getPlayerById(id), PlayerDto.class);
        return players.getBody();
    }

}
