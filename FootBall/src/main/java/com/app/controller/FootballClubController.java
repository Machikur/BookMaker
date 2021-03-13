package com.app.controller;

import com.app.domain.FootballClub;
import com.app.service.FootballClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/football")
public class FootballClubController {

    private final FootballClubService footballClubService;

    public FootballClubController(FootballClubService footballClubService) {
        this.footballClubService = footballClubService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballClub> getClubById(@PathVariable Long id){
        Optional<FootballClub> optionalClub= footballClubService.findById(id);
            return optionalClub.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }


}
