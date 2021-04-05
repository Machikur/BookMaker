package com.app.client.urlbuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
public class FootballClubUrls extends ClientUrl {

    public URI getFootballClubById(Long id) {
        return UriComponentsBuilder.fromHttpUrl(clientUrl)
                .path("/club/{id}")
                .build(Collections.singletonMap("id", id));
    }

    public URI getAllFootballClubs() {
        return UriComponentsBuilder.fromHttpUrl(clientUrl)
                .path("/club/all")
                .build()
                .toUri();
    }

}
