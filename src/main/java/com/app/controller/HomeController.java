package com.app.controller;

import com.app.client.domain.MatchDto;
import com.app.client.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final MatchService matchService;

    public HomeController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<MatchDto> matches = matchService.findAllByFinished(false, 0).getContent();
        model.addAttribute("matches", sortByTime(matches));
        return "home";
    }

    private List<MatchDto> sortByTime(List<MatchDto> matchList) {
        return matchList.stream()
                .sorted(Comparator.comparing(MatchDto::getDateOfMatch)
                        .thenComparing(MatchDto::getFinishTime))
                .collect(Collectors.toList());
    }

}
