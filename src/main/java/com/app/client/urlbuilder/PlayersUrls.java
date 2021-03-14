package com.app.client.urlbuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
public class PlayersUrls {

    @Value("${client.path}")
    private String clientPath;

    public URI getPlayerById(Long id) {
        return UriComponentsBuilder.fromHttpUrl(clientPath)
                .path("player/{id}")
                .build(Collections.singletonMap("id", id));
    }

    public URI getAllPlayerByClubId(Long id) {
        return UriComponentsBuilder.fromHttpUrl(clientPath)
                .path("player/club/{id}")
                .build(Collections.singletonMap("id", id));
    }

}
