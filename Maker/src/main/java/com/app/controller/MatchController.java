package com.app.controller;

import com.app.client.domain.MatchDto;
import com.app.client.domain.PageWrapper;
import com.app.client.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public String matchView(@RequestParam Long id, Model model) {
        MatchDto match = matchService.findById(id);
        model.addAttribute("match", match);
        return "match/matchView";
    }

    @GetMapping("/history")
    public String historyView(@RequestParam(defaultValue = "0") int page, Model model) {
        PageWrapper pageWrapper = matchService.findAllByFinished(true, page);
        model.addAttribute("matches", matchService.sortByDateReversed(pageWrapper.getContent()));
        model.addAttribute("page", pageWrapper);
        return "match/history";
    }


}
