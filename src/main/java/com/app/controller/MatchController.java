package com.app.controller;

import com.app.client.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/match")
@SessionAttributes("match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}")
    public String getMatchById(@PathVariable Long matchId, Model model) {
        model.addAttribute("match", matchService.findById(matchId));
        return "match/matchView";
    }

}
