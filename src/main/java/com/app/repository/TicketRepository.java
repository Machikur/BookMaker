package com.app.repository;

import com.app.domain.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TicketRepository extends CrudRepository<Ticket,Long> {

    Set<Ticket> findAllByDone(boolean done);
}
