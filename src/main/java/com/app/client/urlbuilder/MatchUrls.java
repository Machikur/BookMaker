package com.app.client.urlbuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
public class MatchUrls {

    @Value("${client.path}")
    private String clientPath;

    public URI getAllMatchesByFinished(boolean finished) {
        return UriComponentsBuilder.fromHttpUrl(clientPath)
                .path("match")
                .queryParam("isFinished", finished)
                .build()
                .toUri();
    }

    public URI getAllMatchesByFinished(boolean finished, int page) {
        return UriComponentsBuilder.fromHttpUrl(clientPath)
                .path("match")
                .queryParam("isFinished", finished)
                .queryParam("page", page)
                .build()
                .toUri();
    }

    public URI getMatchById(long matchId) {
        return UriComponentsBuilder.fromHttpUrl(clientPath)
                .path("match/{matchId}")
                .build(Collections.singletonMap("matchId", matchId));
    }

}
