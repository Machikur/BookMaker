package com.app.controller;

import com.app.domain.Goal;
import com.app.dto.GoalDto;
import com.app.mapper.AppMapper;
import com.app.service.data.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/goal")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/{id}")
    public ResponseEntity<GoalDto> getGoalById(@PathVariable Long id) {
        Optional<Goal> optionalGoal = goalService.findById(id);
        return optionalGoal.map(goal -> ResponseEntity.ok(AppMapper.mapToDto(goal)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<Collection<GoalDto>> getGoalsByMatchId(@PathVariable Long matchId) {
        Set<Goal> goals = goalService.findAllByMatchId(matchId);
        return buildResponseEntity(goals);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<Collection<GoalDto>> getGoalsByPlayerId(@PathVariable Long playerId) {
        Set<Goal> goals = goalService.findAllByPlayerId(playerId);
        return buildResponseEntity(goals);
    }

    @GetMapping
    public ResponseEntity<Collection<GoalDto>> getGoalsByDate(@RequestParam LocalDate date) {
        Set<Goal> goals = goalService.findAllByDateOfGoal(date);
        return buildResponseEntity(goals);
    }

    private ResponseEntity<Collection<GoalDto>> buildResponseEntity(Set<Goal> goalSet) {
        return goalSet.size() != 0 ? ResponseEntity.ok(AppMapper.mapToGoalListDto(goalSet)) : ResponseEntity.notFound().build();
    }

}
