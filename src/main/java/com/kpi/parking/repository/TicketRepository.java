package com.kpi.parking.repository;

import com.kpi.parking.domain.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    List<Ticket> findAll();
}
