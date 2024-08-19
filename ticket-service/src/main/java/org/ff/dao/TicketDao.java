package org.ff.dao;

import lombok.AllArgsConstructor;
import org.ff.commonModule.model.Ticket;
import org.ff.repository.TicketRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TicketDao {
    private final TicketRepository ticketRepository;
    public Ticket saveTicket(Ticket ticket){
       return ticketRepository.save(ticket);
    }

}
