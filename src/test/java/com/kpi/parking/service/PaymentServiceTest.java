package com.kpi.parking.service;

import com.kpi.parking.domain.Payment;
import com.kpi.parking.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void givenIdOfTheFirstPayment_whenGetById_thenReturnedPaymentWithGivenId() {
        Optional<Payment> expectedPayment = Optional.of(new Payment(1, LocalDate.parse("2020-11-07"), 1300, "Paid", "Cash"));
        when(paymentRepository.findById(1)).thenReturn(expectedPayment);

        Optional<Payment> actualPayment = paymentRepository.findById(1);

        verify(paymentRepository, times(1)).findById(1);
        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    void getAll() {
        List<Payment> expectedPayments = singletonList(new Payment(1, LocalDate.parse("2020-11-07"), 1300, "Paid", "Cash"));
        when(paymentRepository.findAll()).thenReturn(expectedPayments);

        List<Payment> actualPayments = paymentService.getAll();

        verify(paymentRepository, times(1)).findAll();
        assertEquals(expectedPayments, actualPayments);
    }

    @Test
    void save() {
        Payment payment = new Payment(1, LocalDate.parse("2020-11-07"), 1300, "Paid", "Cash");
        paymentService.save(payment);

        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void update() {
        Payment payment = new Payment(1, LocalDate.parse("2020-11-07"), 1300, "Paid", "Cash");
        when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));

        paymentService.update(payment);

        verify(paymentRepository, times(1)).save(payment);
    }
}