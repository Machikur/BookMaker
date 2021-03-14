package com.app.client.urlbuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
public class FootballClubUrls {

    @Value("${client.path}")
    private String clientPath;

    public URI getFootballClubById(Long id) {
        return UriComponentsBuilder.fromHttpUrl(clientPath)
                .path("/club/{id}")
                .build(Collections.singletonMap("id", id));
    }

    public URI getAllFootballClubs() {
        return UriComponentsBuilder.fromHttpUrl(clientPath)
                .path("/club/all")
                .build()
                .toUri();
    }

}
