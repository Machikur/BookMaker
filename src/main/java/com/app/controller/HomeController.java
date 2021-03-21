package com.app.controller;

import com.app.client.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final MatchService matchService;

    public HomeController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("matches", matchService.findAllByFinished(false,0).getContent());
        return "home";
    }

}
