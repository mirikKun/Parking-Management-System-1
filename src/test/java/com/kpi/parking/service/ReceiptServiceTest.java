package com.kpi.parking.service;

import com.kpi.parking.domain.Spot;
import com.kpi.parking.repository.TicketRepository;
import com.kpi.parking.domain.Ticket;
import com.kpi.parking.domain.Receipt;
import com.kpi.parking.service.ReceiptService;
import org.hibernate.jdbc.Expectations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class ReceiptServiceTest {


    @Mock
    SpotService spotService;
    @InjectMocks
    ReceiptService receiptService;
    @Test
    void check() {
        Optional<Spot> expectedSpot = Optional.of(new Spot(1, true,"Compact", 1,100));
        when(spotService.getById(1)).thenReturn(expectedSpot);

        Receipt expectedReceipt= new Receipt(LocalDateTime.parse("2019-07-13T10:50:00"),LocalDateTime.parse("2019-07-13T13:50:00"),3,100,300);
        Ticket expectedTicket=new Ticket(1, LocalDateTime.parse("2019-07-13T10:50:00"), 1,1);

        when(spotService.getById(1)).thenReturn(expectedSpot);
        Receipt actualReceipt = receiptService.check(expectedTicket,LocalDateTime.parse("2019-07-13T13:50:00"));

        assertEquals(expectedReceipt, actualReceipt);
    }
}