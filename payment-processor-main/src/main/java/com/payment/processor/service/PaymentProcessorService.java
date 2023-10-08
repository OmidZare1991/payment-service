package com.payment.processor.service;

import com.payment.processor.dto.PaymentRequestDto;
import com.payment.processor.util.PaymentType;

public interface PaymentProcessorService {
    void processPayments(PaymentRequestDto paymentDto, PaymentType type);
}
