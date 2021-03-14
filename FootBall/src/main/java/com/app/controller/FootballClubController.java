package com.app.controller;

import com.app.domain.FootballClub;
import com.app.dto.FootballClubDto;
import com.app.mapper.AppMapper;
import com.app.service.data.FootballClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
public class FootballClubController {

    private final FootballClubService footballClubService;

    @GetMapping("/{id}")
    public ResponseEntity<FootballClubDto> getClubById(@PathVariable Long id) {
        Optional<FootballClub> optionalClub = footballClubService.findById(id);
        return optionalClub.map(club -> ResponseEntity.ok(AppMapper.mapToDto(club)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<FootballClubDto>> getAll() {
        Collection<FootballClub> clubs = footballClubService.findAll();
        if (clubs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AppMapper.mapToClubListDto(clubs));
    }

}
