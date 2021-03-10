package com.app.repository;

import com.app.domain.FootballClub;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballClubRepository extends CrudRepository<FootballClub, Long> {

}
