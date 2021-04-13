package com.app.controller;

import com.app.client.service.FootballClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/club")
public class ClubsController {

    private final FootballClubService footballClubService;

    public ClubsController(FootballClubService footballClubService) {
        this.footballClubService = footballClubService;
    }

    @GetMapping
    public String mainView(Model model) {
        model.addAttribute("clubs", footballClubService.getAllClubs());
        return "clubs/mainView";
    }

    @GetMapping("/{id}")
    public String mainView(@PathVariable Long id, Model model) {
        model.addAttribute("club", footballClubService.getClubById(id));
        return "clubs/clubView";
    }

}
