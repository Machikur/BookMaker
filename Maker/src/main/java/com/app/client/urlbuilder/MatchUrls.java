package com.app.client.urlbuilder;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
public class MatchUrls extends ClientUrl {

    public URI getAllMatchesByFinished(boolean finished) {
        return UriComponentsBuilder.fromHttpUrl(clientUrl)
                .path("match")
                .queryParam("isFinished", finished)
                .build()
                .toUri();
    }

    public URI getAllMatchesByFinished(boolean finished, int page) {
        return UriComponentsBuilder.fromHttpUrl(clientUrl)
                .path("match")
                .queryParam("isFinished", finished)
                .queryParam("page", page)
                .build()
                .toUri();
    }

    public URI getMatchById(long matchId) {
        return UriComponentsBuilder.fromHttpUrl(clientUrl)
                .path("match/{matchId}")
                .build(Collections.singletonMap("matchId", matchId));
    }

    public URI getNextMatch() {
        return UriComponentsBuilder.fromHttpUrl(clientUrl)
                .path("match/next")
                .build()
                .toUri();
    }

}
