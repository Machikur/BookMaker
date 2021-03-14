package com.app.repository;

import com.app.domain.ClubStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubStatisticsRepository extends CrudRepository<ClubStatistics, Long> {
}
