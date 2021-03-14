package com.app.controller;

import com.app.domain.Match;
import com.app.dto.MatchDto;
import com.app.mapper.AppMapper;
import com.app.service.data.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDto> findMatchById(@PathVariable Long matchId) {
        return matchService.findById(matchId).map(match -> ResponseEntity.ok(AppMapper.mapToDto(match)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/club/{footballClubId}")
    public ResponseEntity<Collection<MatchDto>> findAllMatchesOfFootballClubId(@PathVariable Long footballClubId) {
        Collection<Match> matches = matchService.findAllMatchesOfFootballClubId(footballClubId);
        return buildResponseEntity(AppMapper.mapToMatchListDto(matches));
    }

    @GetMapping("/date")
    public ResponseEntity<Collection<MatchDto>> findAllByDateOfMatch(@RequestParam LocalDate dateOfMatch) {
        Collection<Match> matches = matchService.findAllByDateOfMatch(dateOfMatch);
        return buildResponseEntity(AppMapper.mapToMatchListDto(matches));
    }

    @GetMapping
    public ResponseEntity<Collection<MatchDto>> findAllFinished(@RequestParam Boolean isFinished) {
        Collection<Match> matches = matchService.findAllByFinished(isFinished);
        return buildResponseEntity(AppMapper.mapToMatchListDto(matches));
    }

    private ResponseEntity<Collection<MatchDto>> buildResponseEntity(Collection<MatchDto> matches) {
        return matches.size() != 0 ? ResponseEntity.ok(matches) : ResponseEntity.notFound().build();
    }

}
