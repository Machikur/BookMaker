package com.app.controller;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;
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
@SessionAttributes({"match", "ticket"})
public class TicketController {

    private final TicketService ticketService;
    private ActiveUser activeUser;

    public TicketController(TicketService ticketService, ActiveUser activeUser) {
        this.ticketService = ticketService;
        this.activeUser = activeUser;
    }

    @GetMapping
    public String getMainView(@RequestParam(required = false) Boolean done, Model model) {
        model.addAttribute("activeTickets", ticketService.findAllByUserIdAndDone(activeUser.getUserId(), done));
        return "user/mainView";
    }

    @GetMapping("/done")
    public String getFilteredMainView(@RequestParam Boolean won, Model model) {
        model.addAttribute("activeTickets", ticketService.findAllDoneByUserIdAndWonTicket(activeUser.getUserId(), won));
        return "user/mainView";
    }

    @GetMapping("/check")
    public String checkTicket(@RequestParam Long id, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        try {
            Ticket result = ticketService.checkTicket(ticketService.findById(id).get(), activeUser.getAccount());
            model.addAttribute("ticket", result);
            updateAccountBalance(request);
        } catch (TicketException s) {
            attributes.addFlashAttribute("error", s.getMessage());
        }
        return "redirect:/ticket";
    }

    @GetMapping("/new")
    public String newTicketView(@ModelAttribute("match") MatchDto match, Model model) {
        model.addAttribute("ticket", ticketService.createTicketForMatch(match, activeUser.getUserId()));
        model.addAttribute("multipliers", ticketService.getMultipliersForMatch(match));
        model.addAttribute("winners", Winner.values());
        return "user/ticketForm";
    }

    @PostMapping("/new")
    public String createNewTicket(@ModelAttribute("ticket") Ticket ticket, @RequestParam Winner winner,
                                  RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ticket.setGuessedWinner(winner);
        try {
            ticketService.validateTicketSaveAndCreatePayment(ticket, activeUser.getAccount());
            updateAccountBalance(request);
        } catch (TicketException | NotEnoughCashException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/";
        }
        return "redirect:/ticket";
    }

    private void updateAccountBalance(HttpServletRequest request) {
        request.getSession().setAttribute("cash", activeUser.getAccount().getCash());
    }

}
