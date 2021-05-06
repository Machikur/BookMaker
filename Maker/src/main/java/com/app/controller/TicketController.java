package com.app.controller;


import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;
import com.app.client.service.MatchService;
import com.app.domain.ActiveUser;
import com.app.domain.Ticket;
import com.app.exception.NotEnoughCashException;
import com.app.exception.TicketException;
import com.app.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ticket")
@SessionAttributes("ticket")
public class TicketController {

    private final MatchService matchService;
    private final TicketService ticketService;
    private ActiveUser activeUser;

    public TicketController(MatchService matchService, TicketService ticketService, ActiveUser activeUser) {
        this.matchService = matchService;
        this.ticketService = ticketService;
        this.activeUser = activeUser;
    }


    @GetMapping
    public String newTicketView(@RequestParam Long matchId, Model model) {
        MatchDto match = matchService.findById(matchId);
        model.addAttribute("ticket", ticketService.createTicketForMatch(match, activeUser.getUserId()));
        model.addAttribute("multipliers", ticketService.getMultipliersForMatch(match));
        model.addAttribute("winners", Winner.values());
        return "user/ticketForm";
    }

    @PostMapping
    public String createNewTicket(@ModelAttribute("ticket") Ticket ticket,
                                  RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            ticketService.validateTicketSaveAndCreatePayment(ticket, activeUser.getAccount());
            updateAccountBalance(request);
        } catch (TicketException | NotEnoughCashException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/";
        }
        return "redirect:/tickets";
    }

    private void updateAccountBalance(HttpServletRequest request) {
        request.getSession().setAttribute("cash", activeUser.getAccount().getCash());
    }
}
