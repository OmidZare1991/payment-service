package com.payment.logger.service;


import com.payment.logger.model.dto.PaymentRequestDto;
import com.payment.logger.model.dto.SystemLogRequestDto;

public interface LoggerService {
    void logErrorDetails(SystemLogRequestDto requestDto);
    void logPaymentDetails(PaymentRequestDto requestDto);
}
