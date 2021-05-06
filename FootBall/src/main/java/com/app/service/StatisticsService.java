package com.app.service;

import com.app.domain.ClubStatistics;
import com.app.repository.ClubStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final ClubStatisticsRepository repository;

    public StatisticsService(ClubStatisticsRepository repository) {
        this.repository = repository;
    }

    public void addPointAndSave(Point point, ClubStatistics statistics) {
        switch (point) {
            case UP:
                statistics.setWinners(statistics.getWinners() + 1);
                break;
            case DOWN:
                statistics.setLosers(statistics.getLosers() + 1);
                break;
            case DRAW:
                statistics.setDraws(statistics.getDraws() + 1);
                break;
            default:
                throw new RuntimeException();
        }
        repository.save(statistics);
    }
}
