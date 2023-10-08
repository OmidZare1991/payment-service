package com.payment.processor.service;

import com.payment.processor.model.dto.SystemLogRequestDto;
import com.payment.processor.model.dto.PaymentRequestDto;

public interface LoggerService {
    void logErrorDetails(SystemLogRequestDto requestDto);
    void logPaymentDetails(PaymentRequestDto requestDto);
}
