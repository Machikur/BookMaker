package com.app.client.urlbuilder;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
public class PlayersUrls extends ClientUrl {

    public URI getPlayerById(Long id) {
        return UriComponentsBuilder.fromHttpUrl(clientUrl)
                .path("player/{id}")
                .build(Collections.singletonMap("id", id));
    }

    public URI getPlayerByName(String name) {
        return UriComponentsBuilder.fromHttpUrl(clientUrl)
                .path("player/check")
                .queryParam("name", name)
                .build()
                .toUri();
    }

}
