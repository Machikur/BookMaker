package com.app.controller;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;
import com.app.client.exception.TicketException;
import com.app.domain.Ticket;
import com.app.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/ticket")
@SessionAttributes({"match","ticket"})
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String getMainView(Model model) {
        model.addAttribute("activeTickets", ticketService.findAllByDone(false));
        return "user/mainView";
    }

    @GetMapping("/{id}")
    public String checkTicket(@PathVariable Long id, Model model) throws TicketException {
        Ticket result = ticketService.checkTicket(ticketService.findById(id).get());
        model.addAttribute("ticket", result);
        return "user/checkTicket";
    }

    @GetMapping("/new")
    public String newTicketView(@ModelAttribute("match") MatchDto match, Model model) {
        model.addAttribute("ticket", ticketService.createTicketForMatch(match));
        model.addAttribute("multipliers", ticketService.getMultipliersForMatch(match));
        model.addAttribute("winners", Winner.values());
        return "user/ticketForm";
    }

    @PostMapping("/new")
    public String createNewTicket(@ModelAttribute("ticket") Ticket ticket,
                                  @RequestParam Winner winner) throws TicketException {
      ticket.setGuessedWinner(winner);
      ticketService.saveTicket(ticket);
        return "redirect:/ticket";
    }
}
