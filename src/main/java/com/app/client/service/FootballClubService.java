package com.app.client.service;

import com.app.client.domain.FootballClubDto;
import com.app.client.urlbuilder.FootballClubUrls;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FootballClubService {

    private final RestTemplate restTemplate;
    private final FootballClubUrls footballClubUrls;

    public FootballClubService(RestTemplate restTemplate, FootballClubUrls footballClubUrls) {
        this.restTemplate = restTemplate;
        this.footballClubUrls = footballClubUrls;
    }

    public FootballClubDto getClubById(Long id) {
        ResponseEntity<FootballClubDto> response =
                restTemplate.getForEntity(footballClubUrls.getFootballClubById(id), FootballClubDto.class);
        return response.getBody();
    }

    public List<FootballClubDto> getAllClubs() {
        ResponseEntity<FootballClubDto[]> response =
                restTemplate.getForEntity(footballClubUrls.getAllFootballClubs(), FootballClubDto[].class);
        return Arrays.asList(response.getBody());
    }

}
