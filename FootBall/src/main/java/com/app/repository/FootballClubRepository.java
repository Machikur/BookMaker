package com.app.repository;

import com.app.domain.FootballClub;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FootballClubRepository extends CrudRepository<FootballClub, Long> {

    @EntityGraph(attributePaths = {"matchesAsHost", "matchesAsOpponent"})
    Optional<FootballClub> findById(Long id);

    @Query("SELECT F.id FROM FootballClub F")
    List<Long> getAllIds();

    Set<FootballClub> findAll();
}
