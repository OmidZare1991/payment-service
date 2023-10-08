package com.payment.processor.strategy;

import com.payment.processor.dto.PaymentRequestDto;
import com.payment.processor.util.PaymentType;

public interface PaymentProcessorStrategy {
    PaymentType getType();
    void processPayments(PaymentRequestDto paymentDto);
}
