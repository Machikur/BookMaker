package com.app.controller;

import com.app.client.service.MatchService;
import com.app.domain.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/match")
@SessionAttributes("match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}")
    public String getMatchById(@PathVariable Long matchId, Model model){
        model.addAttribute("match",matchService.findById(matchId));
        return "match/matchView";
    }

}
