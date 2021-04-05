package com.app.service.system;

import com.app.domain.FootballClub;
import com.app.domain.Match;
import com.app.exception.NotEnoughFootballClubsException;
import com.app.service.data.FootballClubService;
import com.app.service.data.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class RandomMatchSystemManager implements MatchSystemManager {

    private final static int MAX_NOT_FINISHED_MATCHES = 15;
    private final static int START_TIME_HOUR = 10;
    private final static int FINISH_TIME_HOUR = 20;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final MatchService matchService;
    private final MatchManager matchManager;
    private final FootballClubService footballClubService;

    @PostConstruct
    public void createThreadsAfterStart() {
        Set<Match> notFinishedMatches = matchService.findAllByFinished(false);
        notFinishedMatches.forEach(this::createThreadForMatch);
        manage();
    }

    @Override
    public void manage() {
        if (matchService.countAllByFinished(false) < MAX_NOT_FINISHED_MATCHES) {
            Match match = createRandomMatch();
            createThreadForMatch(match);
        }
    }

    private Match createRandomMatch() {
        List<Long> ids = footballClubService.getAllIds();
        if (ids.size() < 2) {
            throw new NotEnoughFootballClubsException();
        }
        Collections.shuffle(ids);
        FootballClub host = footballClubService.findById(ids.get(0)).get();
        FootballClub opponent = footballClubService.findById(ids.get(1)).get();
        Match match = matchService.saveMatch(new Match(host, opponent, getRandomDate(), getRandomTime()));
        log.info("Mecz został utworzony, " + match);
        return match;
    }

    private void createThreadForMatch(Match match) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(countTimeToFinishMatchInMs(match));
                matchManager.doMatch(match.getId());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();
    }

    private LocalDate getRandomDate() {
        return LocalDate.now();
        //return LocalDate.now().plusDays(random.nextLong(2) + 1);
    }

    private LocalTime getRandomTime() {
        return LocalTime.now().minusMinutes(85).minusSeconds(45);
//        int randomHour = random.nextInt(FINISH_HOUR_TIME - START_HOUR_TIME + 1) + START_HOUR_TIME;
//        return LocalTime.of(randomHour, random.nextInt(60));
    }

    private long countTimeToFinishMatchInMs(Match match) {
        long timeToEnd = ChronoUnit.MILLIS.between(LocalDateTime.now(), LocalDateTime.of(match.getDateOfMatch(), match.getFinishTime()));
        return timeToEnd > 0 ? timeToEnd : 1L;
    }

}
