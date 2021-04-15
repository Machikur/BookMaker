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

    public PlayerDto getPLayerByName(String name) {
        ResponseEntity<PlayerDto> player = restTemplate.getForEntity(playersUrls.getPlayerByName(name), PlayerDto.class);
        return player.getBody();
    }

    public PlayerDto getPlayerById(Long id) {
        ResponseEntity<PlayerDto> player = restTemplate.getForEntity(playersUrls.getPlayerById(id), PlayerDto.class);
        return player.getBody();
    }

}
