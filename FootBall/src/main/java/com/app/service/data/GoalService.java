package com.app.service.data;

import com.app.domain.Goal;
import com.app.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public Optional<Goal> findById(Long id) {
        return goalRepository.findById(id);
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


}
