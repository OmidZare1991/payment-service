package com.payment.processor.service;

import com.payment.processor.model.dto.PaymentRequestDto;
import com.payment.processor.model.dto.SystemLogRequestDto;

public interface LoggerService {
    void logErrorDetails(SystemLogRequestDto requestDto);
    void logPaymentDetails(PaymentRequestDto requestDto);
}
