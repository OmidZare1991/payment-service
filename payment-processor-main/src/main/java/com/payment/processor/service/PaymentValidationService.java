package com.payment.processor.service;

import com.payment.processor.model.dto.PaymentRequestDto;

public interface PaymentValidationService {
    boolean validatePaymentDetails(PaymentRequestDto request);
}
