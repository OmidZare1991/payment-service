package com.payment.processor.service.impl;

import com.payment.processor.model.Payment;
import com.payment.processor.repository.PaymentRepository;
import com.payment.processor.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Override
    public void savePayment(Payment payment) {
         paymentRepository.save(payment);
    }
}
