package com.payment.logger.service;


import com.payment.logger.model.Payment;
import com.payment.logger.repository.PaymentRepository;
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
