package com.app.controller;

import com.app.client.service.PlayersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/player")
@Controller
public class PlayerController {

    private final PlayersService playersService;

    public PlayerController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping
    public String getPlayerByIdView(@RequestParam Long id, Model model) {
        model.addAttribute("player", playersService.getPlayerById(id));
        return "player/playerView";
    }

    @GetMapping("/search")
    public String searchView() {
        return "player/findPlayer";
    }


    @GetMapping("/check")
    public String getPlayerByIdView(@RequestParam(required = false) String name, Model model, RedirectAttributes attributes) {
        try {
            model.addAttribute("player", playersService.getPLayerByName(name));
            return "player/playerView";
        } catch (RestClientException s) { }
        attributes.addFlashAttribute("error", "nie znaleziono użytkownika");
        return "redirect:/player/search";
    }

}
