package com.app.controller;

import com.app.client.service.PlayersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/player")
@Controller
public class PlayerController {

    private final PlayersService playersService;

    public PlayerController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping("/{id}")
    public String getPlayerByIdView(@PathVariable Long id, Model model) {
        model.addAttribute("player", playersService.getPlayerById(id));
        return "player/playerView";
    }


}
