package com.app.repository;

import com.app.domain.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @EntityGraph(attributePaths = {"goals", "goals.match", "skills"})
    Optional<Player> findById(Long id);

    Set<Player> findAllByFootballClubId(Long id);
}
