package com.app.controller;

import com.app.domain.ActiveUser;
import com.app.domain.Ticket;
import com.app.exception.TicketException;
import com.app.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tickets")
public class TicketHistoryController {

    private final TicketService ticketService;
    private ActiveUser activeUser;

    public TicketHistoryController(TicketService ticketService, ActiveUser activeUser) {
        this.ticketService = ticketService;
        this.activeUser = activeUser;
    }

    @GetMapping
    public String getMainView(@RequestParam(required = false) Boolean done, @RequestParam(required = false) Boolean won, Model model) {
        if (won != null) {
            model.addAttribute("activeTickets", ticketService.findAllDoneByUserIdAndWonTicket(activeUser.getUserId(), won));
        } else {
            model.addAttribute("activeTickets", ticketService.findAllByUserIdAndDone(activeUser.getUserId(), done));
        }
        return "user/ticketHistory";
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
        return "redirect:/tickets";
    }

    private void updateAccountBalance(HttpServletRequest request) {
        request.getSession().setAttribute("cash", activeUser.getAccount().getCash());
    }

}
