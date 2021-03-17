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
                new Player("SERGIO BUSQUETS", randomSkills(), PlayerPosition.DEFENDER, "https://images2.minutemediacdn.com/image/upload/c_fill,w_720,ar_16:9,f_auto,q_auto,g_auto/shape/cover/sport/FC-Barcelona-v-Villarreal-CF---La-Liga-Santander-ab878364c32066519e1b0349310697f6.jpg"),
                new Player("IVAN RAKITIĆ", randomSkills(), PlayerPosition.DEFENDER, "https://i.guim.co.uk/img/media/27e1625ee229bec7b6a40f032496a3f2459b6d43/0_85_4950_2970/master/4950.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=68de7fd9f1f924720d7a6fc61c7cd474"),
                new Player("GERARD PIQUÉ", randomSkills(), PlayerPosition.DEFENDER, "https://imgresizer.eurosport.com/unsafe/1200x0/filters:format(jpeg):focal(1330x231:1332x229)/origin-imgresizer.eurosport.com/2020/11/23/2942272-60401408-2560-1440.jpg"),
                new Player("NÉLSON SEMEDO", randomSkills(), PlayerPosition.DEFENDER, "https://icdn.football-espana.net/wp-content/uploads/2020/05/nelson-semedo-barcelona.jpg"),
                new Player("Marc-André ter Stegen", randomSkills(), PlayerPosition.GOALKEEPER, "https://www.fcbarcelona.com/photo-resources/2020/12/03/4aba453b-c9c8-4a91-9556-00202e6fffb3/1-Marc-Andre-Ter-Stegen.png?width=600&height=820")
        );

        List<Player> realMadritPlayers = Arrays.asList(
                new Player("Karim Benzema", randomSkills(), PlayerPosition.STRIKER,"https://upload.wikimedia.org/wikipedia/commons/e/e4/Karim_Benzema_2018.jpg"),
                new Player("Lucas Vázquez", randomSkills(), PlayerPosition.STRIKER,"https://images.daznservices.com/di/library/GOAL/b7/a9/lucas-vazquez-real-madrid-2019-20_8qb1az9w6nc616y44cswjn0aw.jpg?t=-145558753&quality=100"),
                new Player("Marco Asensio", randomSkills(), PlayerPosition.STRIKER,"https://img.bleacherreport.net/img/images/photos/003/703/519/hi-res-411f546f18884c727f01dc1ff5df91bf_crop_north.jpg?1508859325&w=3072&h=2048"),
                new Player("Toni Kroos", randomSkills(), PlayerPosition.MIDFIELDER,"https://i.eurosport.com/2020/05/15/2818998-58144988-2560-1440.jpg"),
                new Player("Federico Valverde", randomSkills(), PlayerPosition.MIDFIELDER,"https://en.as.com/en/imagenes/2020/11/09/football/1604943565_824101_noticia_normal.jpg"),
                new Player("Marcelo", randomSkills(), PlayerPosition.DEFENDER,"https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/20180610_FIFA_Friendly_Match_Austria_vs._Brazil_Marcelo_850_1622.jpg/1200px-20180610_FIFA_Friendly_Match_Austria_vs._Brazil_Marcelo_850_1622.jpg"),
                new Player("Nacho Fernández", randomSkills(), PlayerPosition.DEFENDER,"https://d3vlf99qeg6bpx.cloudfront.net/content/uploads/2020/05/19124511/nachofernandez.jpg"),
                new Player("Sergio Ramos", randomSkills(), PlayerPosition.DEFENDER,"https://upload.wikimedia.org/wikipedia/commons/4/43/Russia-Spain_2017_%286%29.jpg"),
                new Player("Éder Militão", randomSkills(), PlayerPosition.DEFENDER,"https://tbrfootball.com/static/uploads/27/2019/10/GettyImages-1169968688.jpg"),
                new Player("Raphaël Varane", randomSkills(), PlayerPosition.DEFENDER,"https://pbs.twimg.com/profile_images/1307599865510322176/ZsgUBJce_400x400.jpg"),
                new Player("Thibaut Courtois", randomSkills(), PlayerPosition.GOALKEEPER,"https://cdn-s-www.republicain-lorrain.fr/images/29DE1820-ABB1-4161-9CA9-9257329C7D90/NW_detail_M/title-1576680650.jpg")
        );

        List<Player> juventusPlayers = Arrays.asList(
                new Player("Federico Bernardeschi", randomSkills(), PlayerPosition.STRIKER,"https://images2.minutemediacdn.com/image/upload/c_fill,w_912,h_516,f_auto,q_auto,g_auto/shape/cover/sport/juventus-v-genoa-cfc-serie-a-5dbc76e3d404073129000001.jpg"),
                new Player("Paulo Dybala", randomSkills(), PlayerPosition.STRIKER,"https://www.mykhel.com/img/2020/12/paulodybala-cropped_17wk09ug6xxiy1hfvzayf9f1ew.jpg"),
                new Player("Álvaro Morata", randomSkills(), PlayerPosition.STRIKER,"https://talksport.com/wp-content/uploads/sites/5/2019/04/GettyImages-1143987718.jpg?strip=all&quality=100&w=1200&h=800&crop=1"),
                new Player("Weston McKennie", randomSkills(), PlayerPosition.MIDFIELDER,"https://s.hs-data.com/bilder/spieler/gross/366327.jpg"),
                new Player("Adrien Rabiot", randomSkills(), PlayerPosition.MIDFIELDER,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGU_z3FDegLTeEhSrOyZwCHlNkifRbjK5yTA&usqp=CAU"),
                new Player("Juan Cuadrado", randomSkills(), PlayerPosition.DEFENDER,"https://tmssl.akamaized.net/images/foto/normal/juan-cuadrado-juventus-turin-1571842182-26837.jpg?lm=1571842210"),
                new Player("Giorgio Chiellini", randomSkills(), PlayerPosition.DEFENDER,"https://icdn.juvefc.com/wp-content/uploads/2020/12/Chiellini-Juventus.jpeg"),
                new Player("Leonardo Bonucci", randomSkills(), PlayerPosition.DEFENDER,"https://upload.wikimedia.org/wikipedia/commons/c/c0/20150616_-_Portugal_-_Italie_-_Gen%C3%A8ve_-_Leonardo_Bonucci_%28cropped%29.jpg"),
                new Player("Merih Demiral", randomSkills(), PlayerPosition.DEFENDER,"https://www.juventus.com/images/image/private/t_editorial_landscape_12_desktop/dev/yo46czrd3udqnule5dnm.jpg"),
                new Player("Matthijs de Ligt", randomSkills(), PlayerPosition.DEFENDER,"https://cdn-wp.thesportsrush.com/2019/07/GettyImages-1142284316.jpg"),
                new Player("Wojciech Szczesny", randomSkills(), PlayerPosition.GOALKEEPER,"https://upload.wikimedia.org/wikipedia/commons/c/c2/Wojciech_Szcz%C4%99sny_2018_%28cropped%29.jpg")
        );

        List<Player> milanPlayers = Arrays.asList(
                new Player("Samu Castillejo", randomSkills(), PlayerPosition.STRIKER,"https://images.daznservices.com/di/library/omnisport/d2/79/samucastillejo-cropped_9p0wk1murnp410exzisgp76qq.jpg?t=-690241183&quality=100"),
                new Player("Jens Petter Hauge", randomSkills(), PlayerPosition.STRIKER,"https://www.unitedinfocus.com/static/uploads/14/2020/12/GettyImages-1289880156.jpg"),
                new Player("Ante Rebic", randomSkills(), PlayerPosition.STRIKER,"https://icdn.sempremilan.com/wp-content/uploads/2019/09/Ante-Rebic-Milano.jpeg"),
                new Player("Rade Krunic", randomSkills(), PlayerPosition.MIDFIELDER,"https://tmssl.akamaized.net/images/foto/normal/rade-krunic-ac-milan-1589797714-38924.jpg?lm=1589797708"),
                new Player("Franck Kessié", randomSkills(), PlayerPosition.MIDFIELDER,"https://s.hs-data.com/bilder/spieler/gross/255807.jpg"),
                new Player("Davide Calabria", randomSkills(), PlayerPosition.DEFENDER,"https://s.hs-data.com/bilder/spieler/gross/237408.jpg"),
                new Player("Theo Hernández", randomSkills(), PlayerPosition.DEFENDER,"https://thelaziali.com/wp-content/uploads/2020/12/Theo-Hernandez-ACM-2020.jpg"),
                new Player("Simon Kjaer", randomSkills(), PlayerPosition.DEFENDER,"https://s.hs-data.com/bilder/spieler/gross/76971.jpg?fallback=png"),
                new Player("Fikayo Tomori", randomSkills(), PlayerPosition.DEFENDER,"https://i.guim.co.uk/img/media/c580af4af1af8329f2f9b262944b0f1525784fde/1014_199_3973_2382/master/3973.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=cb8d9d31baa3e22c1a86a681d36ee1e6"),
                new Player("Alessio Romagnoli", randomSkills(), PlayerPosition.DEFENDER,"https://i.pinimg.com/originals/48/93/67/48936701054c3e84c2dce64e513e14b4.jpg"),
                new Player("Gianluigi Donnarumma", randomSkills(), PlayerPosition.GOALKEEPER,"https://images2.minutemediacdn.com/image/upload/c_fill,w_912,h_516,f_auto,q_auto,g_auto/shape/cover/sport/ac-milan-v-us-lecce-serie-a-5e2d653aa104f924ea000001.jpg")
        );

        List<Player> manchesterPlayers = Arrays.asList(
                new Player("David de Gea", randomSkills(), PlayerPosition.GOALKEEPER,"https://metro.co.uk/wp-content/uploads/2021/03/GettyImages-1304280523.jpg?quality=90&strip=all&zoom=1&resize=644%2C464"),
                new Player("Anthony Martial", randomSkills(), PlayerPosition.STRIKER,"https://img.bleacherreport.net/img/images/photos/003/844/396/hi-res-a0ccd5995f970ea13b04fa2d0402d19a_crop_north.jpg?1577386256&w=3072&h=2048"),
                new Player("Marcus Rashford", randomSkills(), PlayerPosition.STRIKER,"https://www.thesun.co.uk/wp-content/uploads/2020/07/NINTCHDBPICT000595791292-1.jpg"),
                new Player("Paul Pogba", randomSkills(), PlayerPosition.MIDFIELDER,"https://s.hs-data.com/bilder/spieler/gross/180976.jpg"),
                new Player("Juan Mata", randomSkills(), PlayerPosition.MIDFIELDER,"https://www.unitedinfocus.com/static/uploads/14/2020/08/GettyImages-1227925741.jpg"),
                new Player("Victor Lindelöf", randomSkills(), PlayerPosition.DEFENDER,"https://tmssl.akamaized.net/images/foto/normal/victor-lindelof-manchester-united-1542105670-18798.jpg?lm=1542105682"),
                new Player("Eric Bailly", randomSkills(), PlayerPosition.DEFENDER,"https://s.hs-data.com/bilder/spieler/gross/258861.jpg"),
                new Player("Phil Jones", randomSkills(), PlayerPosition.DEFENDER,"https://e0.365dm.com/20/08/768x432/skysports-phil-jones-manchester-united_5057868.jpg?20200812213648"),
                new Player("Harry Maguire", randomSkills(), PlayerPosition.DEFENDER,"https://e0.365dm.com/20/10/2048x1152/skysports-harry-maguire-manchester-united_5115910.jpg"),
                new Player("Diogo Dalot", randomSkills(), PlayerPosition.DEFENDER,"https://www.unitedinfocus.com/static/uploads/14/2020/03/GettyImages-1206134229.jpg"),
                new Player("Mason Greenwood", randomSkills(), PlayerPosition.STRIKER,"https://specials-images.forbesimg.com/imageserve/5fb4615cb1754b272321222b/960x0.jpg?cropX1=266&cropX2=3852&cropY1=7&cropY2=2902")
        );

        FootballClub barcelona = new FootballClub("Barcelona", "FCB", "https://icons.iconarchive.com/icons/giannis-zographos/spanish-football-club/256/FC-Barcelona-icon.png");
        FootballClub realMadrit = new FootballClub("Real Madrit", "RM", "https://icons.iconarchive.com/icons/giannis-zographos/spanish-football-club/256/Real-Madrid-icon.png");
        FootballClub juventus = new FootballClub("Juventus", "Juventus", "https://icons.iconarchive.com/icons/giannis-zographos/italian-football-club/256/Juventus-icon.png");
        FootballClub milan = new FootballClub("A.C. Milan", "Milan", "https://icons.iconarchive.com/icons/giannis-zographos/italian-football-club/256/AC-Milan-icon.png");
        FootballClub manchester = new FootballClub("Manchester United", "Man Utd", "https://icons.iconarchive.com/icons/giannis-zographos/english-football-club/256/Manchester-United-icon.png");

        barcelonaPlayers.forEach(p -> p.setFootballClub(barcelona));
        realMadritPlayers.forEach(p -> p.setFootballClub(realMadrit));
        juventusPlayers.forEach(p -> p.setFootballClub(juventus));
        milanPlayers.forEach(p -> p.setFootballClub(milan));
        manchesterPlayers.forEach(p -> p.setFootballClub(manchester));

        footballClubService.saveFootballClub(barcelona);
        footballClubService.saveFootballClub(realMadrit);
        footballClubService.saveFootballClub(juventus);
        footballClubService.saveFootballClub(milan);
        footballClubService.saveFootballClub(manchester);
    }

    private Skills randomSkills() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new Skills(random.nextInt(30)+70, random.nextInt(30)+70, random.nextInt(30)+70);
    }

}
