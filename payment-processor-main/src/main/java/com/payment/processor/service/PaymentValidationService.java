package com.payment.processor.service;

import com.payment.processor.dto.PaymentRequestDto;

public interface PaymentValidationService {
    boolean validatePaymentDetails(PaymentRequestDto request);
}
