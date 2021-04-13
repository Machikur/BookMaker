package com.app.client.service;

import com.app.client.domain.MatchDto;
import com.app.client.domain.PageWrapper;
import com.app.client.urlbuilder.MatchUrls;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchService {

    private final MatchUrls matchUrls;
    private final RestTemplate restTemplate;

    public MatchService(MatchUrls matchUrls, RestTemplate restTemplate) {
        this.matchUrls = matchUrls;
        this.restTemplate = restTemplate;
    }


    public PageWrapper findAllByFinished(boolean finished, int page) {
        ResponseEntity<PageWrapper> result = restTemplate.getForEntity(matchUrls.getAllMatchesByFinished(finished, page), PageWrapper.class);
        return result.getBody();
    }

    public MatchDto findFirstMatchInTheQue() {
        ResponseEntity<MatchDto> result = restTemplate.getForEntity(matchUrls.getNextMatch(), MatchDto.class);
        return result.getBody();
    }

    public MatchDto findById(long id) {
        ResponseEntity<MatchDto> result = restTemplate.getForEntity(matchUrls.getMatchById(id), MatchDto.class);
        return result.getBody();
    }

}
