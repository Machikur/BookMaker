package com.app.service;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;
import com.app.client.service.MatchService;
import com.app.domain.Account;
import com.app.domain.Payment;
import com.app.domain.Ticket;
import com.app.exception.NotEnoughCashException;
import com.app.exception.TicketException;
import com.app.repository.TicketRepository;
import com.app.system.TicketManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MatchService matchService;
    private final TicketManager ticketManager;
    private final AccountService accountService;
    private final PaymentService paymentService;

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public int countAllByUserId(Long userId) {
        return ticketRepository.countAllByUserId(userId);
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket validateTicketSaveAndCreatePayment(Ticket ticket, Account account) throws TicketException, NotEnoughCashException {
        MatchDto matchDto = matchService.findById(ticket.getMatchId());
        if (LocalDateTime.now().isAfter(LocalDateTime.of(matchDto.getDateOfMatch(), matchDto.getFinishTime()))) {
            throw new TicketException("Mecz już się zakończył");
        }
        accountService.takeCash(ticket.getQuote(), account);
        ticket.setQuoteToWin(ticket.getQuote().multiply(BigDecimal.valueOf(ticketManager.countMultiplierByWinnerType(matchDto, ticket.getGuessedWinner()))));
        Payment payment = new Payment(account, ticket.getQuote(), false);
        paymentService.savePayment(payment);
        return saveTicket(ticket);
    }

    public Ticket createTicketForMatch(MatchDto matchDto, Long userId) {
        Ticket ticket = new Ticket();
        ticket.setMatchId(matchDto.getId());
        ticket.setOpTeam(matchDto.getOppositeTeam().getName());
        ticket.setHostTeam(matchDto.getHostTeam().getName());
        ticket.setUserId(userId);
        ticket.setMatchDate(matchDto.getDateOfMatch());
        ticket.setMatchTime(matchDto.getFinishTime());
        return ticket;
    }

    public Map<Winner, Double> getMultipliersForMatch(MatchDto matchDto) {
        return ticketManager.getPricesForMatch(matchDto);
    }

    public Set<Ticket> findAllByUserIdAndDone(Long userId, Boolean done) {
        return done == null ? ticketRepository.findAllByUserId(userId) : ticketRepository.findAllByUserIdAndDone(userId, done);
    }

    public Set<Ticket> findAllDoneByUserIdAndWonTicket(Long userId, boolean won) {
        return ticketRepository.findAllByUserIdAndDoneAndWonTicket(userId, true, won);
    }

    @Transactional
    public Ticket checkTicket(Ticket ticket, Account account) throws TicketException {
        if (ticket.isDone()) {
            throw new TicketException("Zakład został już rozliczony!");
        }
        MatchDto match = matchService.findById(ticket.getMatchId());
        if (match.getFinished().equals(Boolean.FALSE)) {
            throw new TicketException("Mecz się jeszcze nie skończył!");
        }
        if (ticket.getGuessedWinner().equals(match.getWinner())) {
            payAndSetWon(ticket, account);
        } else {
            ticket.setWonTicket(false);
        }
        ticket.setDone(true);
        ticket.setResult(match.getResult());
        return saveTicket(ticket);
    }

    private void payAndSetWon(Ticket ticket, Account account) {
        Payment payment = new Payment(account, ticket.getQuote(), true);
        paymentService.savePayment(payment);
        BigDecimal quoteToPay = ticket.getQuoteToWin();
        accountService.putCash(quoteToPay, account);
        ticket.setWonTicket(true);
    }

}
