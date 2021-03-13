package com.app.service;

import com.app.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class MatchEngineTest {

    @Autowired
    private MatchCreator matchCreator;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private FootballClubService footballClubService;

    @Autowired
    private MatchService matchService;

    @Test
    void doMatch() {
        //given
        List<Player> playerList1 = Arrays.asList(
                new Player("Ronaldinho", randomSkills(), PlayerPosition.STRIKER),
                new Player("Messi1", randomSkills(), PlayerPosition.STRIKER),
                new Player("Messi2", randomSkills(), PlayerPosition.STRIKER),
                new Player("Messi3", randomSkills(), PlayerPosition.STRIKER),
                new Player("Messi4", randomSkills(), PlayerPosition.MIDFIELDER),
                new Player("Messi5", randomSkills(), PlayerPosition.MIDFIELDER),
                new Player("Messi6", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi7", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi8", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi9", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi10", randomSkills(), PlayerPosition.GOALKEEPER)
        );
        List<Player> playerList2 = Arrays.asList(
                new Player("Ronaldinho", randomSkills(), PlayerPosition.STRIKER),
                new Player("Messi11", randomSkills(), PlayerPosition.STRIKER),
                new Player("Messi12", randomSkills(), PlayerPosition.STRIKER),
                new Player("Messi13", randomSkills(), PlayerPosition.MIDFIELDER),
                new Player("Messi14", randomSkills(), PlayerPosition.MIDFIELDER),
                new Player("Messi15", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi16", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi17", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi18", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi19", randomSkills(), PlayerPosition.DEFENDER),
                new Player("Messi20", randomSkills(), PlayerPosition.GOALKEEPER)
        );

        playerList1.forEach(p->playerService.savePlayer(p));
        playerList2.forEach(p->playerService.savePlayer(p));

        FootballClub club1 = new FootballClub("Wisła-Kraków", "WIK");
        FootballClub club2 = new FootballClub("Legia-Warszawa", "LEW");

        playerList1.forEach(p->p.setFootballClub(club1));
        playerList2.forEach(p->p.setFootballClub(club2));

        playerList1.forEach(p->playerService.savePlayer(p));
        playerList2.forEach(p->playerService.savePlayer(p));

        footballClubService.saveFootballClub(club1);
        footballClubService.saveFootballClub(club2);

        Match match = new Match(club1, club2, LocalDate.now(), LocalTime.now().minusMinutes(90));
        Match match2 = new Match(club1, club2, LocalDate.now(), LocalTime.now().minusMinutes(90));

        //when
        match = matchCreator.doMatch(match);
        match2 = matchCreator.doMatch(match2);

        //then
        System.out.println(match);
        System.out.println(match2);

    }

    private Skills randomSkills() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new Skills(random.nextInt(100), random.nextInt(100), random.nextInt(100));
    }

}