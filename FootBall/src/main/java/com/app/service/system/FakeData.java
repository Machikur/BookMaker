package com.app.service.system;

import com.app.domain.FootballClub;
import com.app.domain.Player;
import com.app.domain.PlayerPosition;
import com.app.domain.Skills;
import com.app.service.FootballClubService;
import com.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class FakeData {

    private final PlayerService playerService;
    private final FootballClubService footballClubService;

    @PostConstruct
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

        FootballClub club1 = new FootballClub("Wisła-Kraków", "WIK");
        FootballClub club2 = new FootballClub("Legia-Warszawa", "LEW");

        playerList1.forEach(p -> p.setFootballClub(club1));
        playerList2.forEach(p -> p.setFootballClub(club2));

        footballClubService.saveFootballClub(club1);
        footballClubService.saveFootballClub(club2);

    }

    private Skills randomSkills() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new Skills(random.nextInt(100), random.nextInt(100), random.nextInt(100));
    }
}
