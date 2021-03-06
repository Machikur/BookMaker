package com.app.controller;

import com.app.client.domain.MatchDto;
import com.app.client.service.MatchService;
import com.app.service.TimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("match")
@RequestMapping("/")
public class HomeController {

    private final MatchService matchService;
    private final TimeService timeService;

    public HomeController(MatchService matchService, TimeService timeService) {
        this.matchService = matchService;
        this.timeService = timeService;
    }

    @GetMapping
    public String home(Model model) {
        MatchDto match = matchService.findFirstMatchInTheQue();
        List<MatchDto> matches = matchService.findAllByFinished(false, 0).getContent();
        model.addAttribute("match", match);
        model.addAttribute("matches", matchService.sortByDate(matches));
        model.addAttribute("time", timeService.getTimeToFinish(match));
        return "home";
    }


}
