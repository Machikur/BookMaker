package com.app.repository;

import com.app.domain.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

    Set<Ticket> findAllByUserIdAndDone(Long userId, boolean done);

    Set<Ticket> findAllByUserId(Long userId);

    Set<Ticket> findAllByUserIdAndDoneAndWonTicket(Long userId, boolean done, boolean won);


}
