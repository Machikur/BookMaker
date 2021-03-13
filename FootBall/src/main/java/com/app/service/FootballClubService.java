package com.app.service;

import com.app.domain.FootballClub;
import com.app.repository.FootballClubRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FootballClubService {

    private final FootballClubRepository footballClubRepository;

    public FootballClubService(FootballClubRepository footballClubRepository) {
        this.footballClubRepository = footballClubRepository;
    }

    public FootballClub saveFootballClub(FootballClub footballClub){
        return footballClubRepository.save(footballClub);
    }

    public Optional<FootballClub> findById(Long id){
        return footballClubRepository.findById(id);
    }

}
