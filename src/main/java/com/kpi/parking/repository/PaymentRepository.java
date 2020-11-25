package com.kpi.parking.repository;

import com.kpi.parking.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    List<Payment> findAll();

}
