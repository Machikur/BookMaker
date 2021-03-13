package com.app.controller;

import com.app.domain.Goal;
import com.app.service.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/goal")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
        Optional<Goal> optionalGoal = goalService.findById(id);
        return optionalGoal.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<Set<Goal>> getGoalsByMatchId(@PathVariable Long matchId) {
        Set<Goal> goals = goalService.findAllByMatchId(matchId);
        return returnSetOrNotFoundIfSizeEqualsZero(goals);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<Set<Goal>> getGoalsByPlayerId(@PathVariable Long playerId) {
        Set<Goal> goals = goalService.findAllByPlayerId(playerId);
        return returnSetOrNotFoundIfSizeEqualsZero(goals);
    }

    @GetMapping("/{date}")
    public ResponseEntity<Set<Goal>> getGoalsByDate(@PathVariable LocalDate date) {
        Set<Goal> goals = goalService.findAllByDateOfGoal(date);
        return returnSetOrNotFoundIfSizeEqualsZero(goals);
    }

    private ResponseEntity<Set<Goal>> returnSetOrNotFoundIfSizeEqualsZero(Set<Goal> collection){
        if (collection.size() != 0) {
            return ResponseEntity.ok(collection);
        } else return ResponseEntity.notFound().build();
    }

}
