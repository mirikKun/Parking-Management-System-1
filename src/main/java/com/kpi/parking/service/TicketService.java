package com.kpi.parking.service;

import com.kpi.parking.domain.Ticket;
import com.kpi.parking.exception.EntityNotFoundException;
import com.kpi.parking.exception.UsernameNotUniqueException;
import com.kpi.parking.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public Optional<Ticket> getById(int id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void update(Ticket ticket) {
        verifyTicketPresent(ticket.getId());
        ticketRepository.save(ticket);
    }

    public void delete(int id) {
        ticketRepository.deleteById(id);
    }

    private void verifyTicketPresent(int id) {
        ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Ticket with id %d is not present", id)));
    }
}
