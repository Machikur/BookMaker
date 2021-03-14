package com.app.service.data;

import com.app.domain.FootballClub;
import com.app.repository.FootballClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FootballClubService {

    private final FootballClubRepository footballClubRepository;

    public FootballClubService(FootballClubRepository footballClubRepository) {
        this.footballClubRepository = footballClubRepository;
    }

    public FootballClub saveFootballClub(FootballClub footballClub) {
        return footballClubRepository.save(footballClub);
    }

    public Optional<FootballClub> findById(Long id) {
        return footballClubRepository.findById(id);
    }

    public List<Long> getAllIds() {
        return footballClubRepository.getAllIds();
    }

    public Set<FootballClub> findAll() {
        return footballClubRepository.findAll();
    }


}
