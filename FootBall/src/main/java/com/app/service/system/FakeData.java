package com.app.service.system;

import com.app.domain.FootballClub;
import com.app.domain.Player;
import com.app.domain.PlayerPosition;
import com.app.domain.Skills;
import com.app.service.data.FootballClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class FakeData {

    private final FootballClubService footballClubService;

    @PostConstruct
    void createData() {
        List<Player> barcelonaPlayers = Arrays.asList(
                new Player("RONALDINHO", randomSkills(), PlayerPosition.STRIKER, "https://images.daznservices.com/di/library/GOAL/1b/c5/ronaldinho-barcelona_1slsxexo4gn5z1m0cln77mlj3h.jpg?t=100890353&quality=60&w=1200&h=800"),
                new Player("MARTIN BRAITHWAITE", randomSkills(), PlayerPosition.STRIKER, "https://pbs.twimg.com/profile_images/1231549032943800320/WR769kKG_400x400.jpg"),
                new Player("FRENKIE DE JONG", randomSkills(), PlayerPosition.STRIKER, "https://icdn.football-espana.net/wp-content/uploads/2020/11/frenkiedejong-barcelona10.jpeg"),
                new Player("PEDRI GONZÁLEZ", randomSkills(), PlayerPosition.STRIKER, "https://www.fcbarcelonanoticias.com/uploads/s1/12/37/24/4/pedri-barca-match.jpeg"),
                new Player("RONALD ARAUJO", randomSkills(), PlayerPosition.MIDFIELDER, "https://www.fcbarcelonanoticias.com/uploads/s1/12/40/07/2/araujo-entrevista-barca.jpeg"),
                new Player("JEAN-CLAIR TODIBO", randomSkills(), PlayerPosition.MIDFIELDER, "https://img.bleacherreport.net/img/images/photos/003/847/558/hi-res-4b698610152c79add23d850ba395aa18_crop_north.jpg?1579115390&w=3072&h=2048"),
                new Player("SERGIO BUSQUETS", randomSkills(), PlayerPosition.DEFENDER, "https://www.fcbarcelona.com/photo-resources/2020/10/08/ac507a09-945f-48c7-8937-2cd3b65992a2/mini_busquets.png?width=670&height=790"),
                new Player("IVAN RAKITIĆ", randomSkills(), PlayerPosition.DEFENDER, "https://s.yimg.com/xe/i/us/sp/v/soccer/wc/players/373225.5.png"),
                new Player("GERARD PIQUÉ", randomSkills(), PlayerPosition.DEFENDER, "https://s.yimg.com/xe/i/us/sp/v/soccer/wc/players/372857.4.png"),
                new Player("NÉLSON SEMEDO", randomSkills(), PlayerPosition.DEFENDER, "https://icdn.football-espana.net/wp-content/uploads/2020/05/nelson-semedo-barcelona.jpg"),
                new Player("Marc-André ter Stegen", randomSkills(), PlayerPosition.GOALKEEPER, "https://s.yimg.com/xe/i/us/sp/v/soccer/wc/players/375205.3.png")
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

        List<Player> playerList3 = Arrays.asList(
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

        List<Player> playerList4 = Arrays.asList(
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

        List<Player> playerList5 = Arrays.asList(
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


        FootballClub barcelona = new FootballClub("Barcelona", "FCB", "https://icons.iconarchive.com/icons/giannis-zographos/spanish-football-club/256/FC-Barcelona-icon.png");
        FootballClub club2 = new FootballClub("Legia-Warszawa", "LEW", "https://icons.iconarchive.com/icons/giannis-zographos/spanish-football-club/256/Real-Madrid-icon.png");
        FootballClub club3 = new FootballClub("Legia", "LEW", "https://icons.iconarchive.com/icons/giannis-zographos/italian-football-club/256/Juventus-icon.png");
        FootballClub club4 = new FootballClub("Warszawa", "LEW", "https://icons.iconarchive.com/icons/giannis-zographos/italian-football-club/256/AC-Milan-icon.png");
        FootballClub club5 = new FootballClub("Legia-Kraków", "LEW", "https://icons.iconarchive.com/icons/giannis-zographos/english-football-club/256/Manchester-United-icon.png");

        barcelonaPlayers.forEach(p -> p.setFootballClub(barcelona));
        playerList2.forEach(p -> p.setFootballClub(club2));
        playerList3.forEach(p -> p.setFootballClub(club3));
        playerList4.forEach(p -> p.setFootballClub(club4));
        playerList5.forEach(p -> p.setFootballClub(club5));

        footballClubService.saveFootballClub(barcelona);
        footballClubService.saveFootballClub(club2);
        footballClubService.saveFootballClub(club3);
        footballClubService.saveFootballClub(club4);
        footballClubService.saveFootballClub(club5);
    }

    private Skills randomSkills() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new Skills(random.nextInt(100), random.nextInt(100), random.nextInt(100));
    }


}
