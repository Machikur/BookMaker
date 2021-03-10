package com.app.service;

import com.app.domain.Goal;
import com.app.domain.Player;
import com.app.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal SaveGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public Set<Goal> findAllByPlayerId(Long id) {
        return goalRepository.findAllByPlayerId(id);
    }

    public Set<Goal> findAllByMatchId(Long id) {
        return goalRepository.findAllByMatchId(id);
    }

    public Set<Goal> findAllByDateOfGoal(LocalDate date) {
        return goalRepository.findAllByDateOfGoal(date);
    }

    public int countAllByMatchId(Long matchId) {
        return goalRepository.countAllByMatchId(matchId);
    }

}
