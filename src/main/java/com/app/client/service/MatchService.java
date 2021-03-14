package com.app.client.service;

import com.app.client.domain.MatchDto;
import com.app.client.urlbuilder.MatchUrls;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class MatchService {

    private final MatchUrls matchUrls;
    private final RestTemplate restTemplate;

    public MatchService(MatchUrls matchUrls, RestTemplate restTemplate) {
        this.matchUrls = matchUrls;
        this.restTemplate = restTemplate;
    }

    public List<MatchDto> findAllByFinished(boolean finished) {
        ResponseEntity<MatchDto[]> result = restTemplate.getForEntity(matchUrls.getAllMatchesByFinished(finished), MatchDto[].class);
        return Arrays.asList(result.getBody());
    }

    public MatchDto findById(long id) {
        ResponseEntity<MatchDto> result = restTemplate.getForEntity(matchUrls.getMatchById(id), MatchDto.class);
        return result.getBody();
    }
}
