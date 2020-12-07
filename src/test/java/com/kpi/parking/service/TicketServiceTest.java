package com.kpi.parking.service;

import com.kpi.parking.domain.Ticket;
import com.kpi.parking.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void givenIdOfTheFirstTicket_whenGetById_thenReturnedTicketWithGivenId() {
        Optional<Ticket> expectedTicket = Optional.of(new Ticket(1, LocalDateTime.parse("2019-07-13T10:50:00"), 1,1));
        when(ticketRepository.findById(1)).thenReturn(expectedTicket);

        Optional<Ticket> actualTicket = ticketService.getById(1);

        verify(ticketRepository, times(1)).findById(1);
        assertEquals(expectedTicket, actualTicket);
    }

    @Test
    void getAll() {
        List<Ticket> expectedTickets = singletonList(new Ticket(1, LocalDateTime.parse("2019-07-13T10:50:00"), 1,1));
        when(ticketRepository.findAll()).thenReturn(expectedTickets);

        List<Ticket> actualTickets = ticketService.getAll();

        verify(ticketRepository, times(1)).findAll();
        assertEquals(expectedTickets, actualTickets);
    }

    @Test
    void save() {
        Ticket ticket = new Ticket(1, LocalDateTime.parse("2019-07-13T10:50:00"), 1,1);
        when(ticketRepository.findByPaymentId(1)).thenReturn(Optional.empty());

        ticketService.save(ticket);

        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void update() {
        Ticket ticket = new Ticket(1, LocalDateTime.parse("2019-07-13T10:50:00"), 1,1);
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        when(ticketRepository.findByPaymentId(1)).thenReturn(Optional.empty());

        ticketService.update(ticket);

        verify(ticketRepository, times(1)).save(ticket);
    }
}