package com.app.repository;

import com.app.domain.Goal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface GoalRepository extends CrudRepository<Goal, Long> {

    Set<Goal> findAllByPlayerId(Long id);

    Set<Goal> findAllByMatchId(Long id);

    Set<Goal> findAllByDateOfGoal(LocalDate date);

    int countAllByMatchId(Long id);
}
