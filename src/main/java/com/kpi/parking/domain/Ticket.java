package com.kpi.parking.domain;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="payment_id")
    private int paymentId;
    @Column(name="creation_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate creationDate;

    public Ticket() {
    }
    public Ticket(int id, LocalDate creationDate, int paymentId ){
        this.id = id;
        this.paymentId = paymentId;
        this.creationDate = creationDate;
    }

    public Ticket(LocalDate creationDate, int paymentId) {

        this.paymentId = paymentId;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                paymentId == ticket.paymentId &&
                creationDate.equals(ticket.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentId, creationDate);
    }
}
