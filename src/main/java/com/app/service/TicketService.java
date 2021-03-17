package com.app.service;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;
import com.app.client.exception.TicketException;
import com.app.client.service.MatchService;
import com.app.system.TicketManager;
import com.app.domain.Ticket;
import com.app.domain.User;
import com.app.repository.TicketRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MatchService matchService;
    private final TicketManager ticketManager;

    public TicketService(TicketRepository ticketRepository, MatchService matchService, TicketManager ticketManager) {
        this.ticketRepository = ticketRepository;
        this.matchService = matchService;
        this.ticketManager = ticketManager;
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket saveTicket(Ticket ticket) throws TicketException {
        MatchDto matchDto=matchService.findById(ticket.getMatchId());
        if (LocalDateTime.now().isBefore(LocalDateTime.of(matchDto.getDateOfMatch(),matchDto.getFinishTime()))) {
            ticket.setQuoteToWin(ticket.getQuote().multiply(BigDecimal.valueOf(ticketManager.countMultiplierByWinnerType(matchDto, ticket.getGuessedWinner()))));
            return ticketRepository.save(ticket);
        }
        throw new TicketException("Mecz już się zakończył");
    }

    public Ticket createTicketForMatch(MatchDto matchDto) {
        Ticket ticket = new Ticket();
        ticket.setMatchId(matchDto.getId());
        ticket.setOpTeam(matchDto.getOppositeTeam().getName());
        ticket.setHostTeam(matchDto.getHostTeam().getName());
        return ticket;
    }

    public Map<Winner,Double> getMultipliersForMatch(MatchDto matchDto){
        return ticketManager.getPricesForMatch(matchDto);
    }


    public Set<Ticket> findAllByDone(boolean done) {
        return ticketRepository.findAllByDone(done);
    }

    @Transactional
    public Ticket checkTicket(Ticket ticket) throws TicketException {
        if (!ticket.isDone()) {
            MatchDto match = matchService.findById(ticket.getMatchId());
            if (match.getFinished().equals(Boolean.TRUE)) {
                if (ticket.getGuessedWinner().equals(match.getWinner())) {
                    payAndSetWon(ticket);
                } else {
                    ticket.setWonTicket(false);
                }
                ticket.setDone(true);
                saveTicket(ticket);
            }
        }
        throw new TicketException("Mecz się jeszcze nie zakończył");
    }

    private void payAndSetWon(Ticket ticket) {
        BigDecimal quoteToPay = ticket.getQuoteToWin();
        User user = ticket.getUser();
        user.putCash(quoteToPay);
        ticket.setWonTicket(true);
    }

}
