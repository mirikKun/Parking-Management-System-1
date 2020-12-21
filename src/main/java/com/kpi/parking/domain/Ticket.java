package com.kpi.parking.domain;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="creation_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationDate;

    @Column(name = "spot_id")
    private int spotId;

    public Ticket() {
    }
    public Ticket(int id, LocalDateTime creationDate, int paymentId ,int spotId){
        this.id = id;
        this.creationDate = creationDate;
        this.spotId=spotId;
    }

    public Ticket(LocalDateTime creationDate, int paymentId,int spotId) {
        this.creationDate = creationDate;
        this.spotId=spotId;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                creationDate.equals(ticket.creationDate) &&
                spotId==ticket.spotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate,spotId);
    }
}
