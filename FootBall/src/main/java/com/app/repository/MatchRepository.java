package com.app.repository;

import com.app.domain.Match;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    @EntityGraph(attributePaths = {"goals"})
    Optional<Match> findById(Long id);

    Set<Match> findAllByOppositeTeamIdOrHostTeamId(Long id, Long id2);

    Set<Match> findAllByDateOfMatch(LocalDate startTime);

    Set<Match> findAllByFinished(boolean finished);
}
