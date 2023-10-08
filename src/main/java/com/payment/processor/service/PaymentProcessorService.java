package com.payment.processor.service;

import com.payment.processor.model.dto.PaymentRequestDto;

public interface PaymentProcessorService {
    void processPayments(PaymentRequestDto paymentDto);
}
