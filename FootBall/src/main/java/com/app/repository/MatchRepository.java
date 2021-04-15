package com.app.repository;

import com.app.domain.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {

    @EntityGraph(attributePaths = {"goals", "hostTeam", "oppositeTeam"})
    Optional<Match> findById(Long id);

    Set<Match> findAllByOppositeTeamIdOrHostTeamId(Long id, Long id2);

    Set<Match> findAllByDateOfMatch(LocalDate startTime);

    Set<Match> findAllByFinished(boolean finished);

    Page<Match> findAllByFinishedOrderByDateOfMatchDescStartTimeDesc(boolean finished, Pageable pageable);

    int countAllByFinished(boolean finished);


}
