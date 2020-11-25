package com.kpi.parking.service;

import com.kpi.parking.domain.Account;
import com.kpi.parking.domain.Payment;
import com.kpi.parking.exception.EntityNotFoundException;
import com.kpi.parking.exception.UsernameNotUniqueException;
import com.kpi.parking.repository.AccountRepository;
import com.kpi.parking.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) { this.paymentRepository = paymentRepository; }

    public Optional<Payment> getById(int id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public void save(Payment payment) { paymentRepository.save(payment); }

    public void update(Payment payment) {
        verifyPaymentPresent(payment.getId());
        paymentRepository.save(payment);
    }

    public void delete(int id) {
        paymentRepository.deleteById(id);
    }

    private void verifyPaymentPresent(int id) {
        paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Payment with id %d is not present", id)));
    }
}
