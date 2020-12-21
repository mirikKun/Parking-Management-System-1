package com.kpi.parking.service;

import com.kpi.parking.domain.Receipt;
import com.kpi.parking.domain.Spot;
import com.kpi.parking.domain.Ticket;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ReceiptService {

    private final SpotService spotService;

    public ReceiptService(SpotService spotService) {
        this.spotService = spotService;
    }

    public Receipt check(Ticket ticket, LocalDateTime endTime) {
        LocalDateTime creationDate = ticket.getCreationDate();
        Duration parkingTime = Duration.between(creationDate, endTime);
        Spot spot = spotService.getById(ticket.getSpotId()).get();
        int fee = spot.getFee();
        long seconds = parkingTime.getSeconds();
        long hours = seconds / 3600;
        long totalCost = Math.round(fee * hours);
        return new Receipt(creationDate, endTime, (int)hours, fee, (int) totalCost);
    }

}
