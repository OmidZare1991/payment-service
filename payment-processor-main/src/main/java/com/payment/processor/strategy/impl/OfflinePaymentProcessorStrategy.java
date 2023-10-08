package com.payment.processor.strategy.impl;

import com.payment.processor.model.dto.SystemLogRequestDto;
import com.payment.processor.model.dto.PaymentRequestDto;
import com.payment.processor.strategy.PaymentProcessorStrategy;
import com.payment.processor.util.ErrorType;
import com.payment.processor.util.PaymentType;
import com.payment.processor.service.LoggerService;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfflinePaymentProcessorStrategy implements PaymentProcessorStrategy {
    private final LoggerService loggerService;
    @Override
    public PaymentType getType() {
        return PaymentType.OFFLINE;
    }

    @Override
    public void processPayments(PaymentRequestDto paymentDto) {
        String paymentId = paymentDto.getPaymentId();
        try {
            loggerService.logPaymentDetails(paymentDto);
        } catch (Exception e) {
            SystemLogRequestDto systemLogRequest = createSystemLogRequest(e, paymentId);
            loggerService.logErrorDetails(systemLogRequest);
        }
    }

    private SystemLogRequestDto createSystemLogRequest(Exception exception, String paymentId) {
        String errorType;
        String errorDescription;

        if (isDatabaseException(exception)) {
            errorType = ErrorType.DATABASE.getValue();
        } else {
            errorType = ErrorType.OTHERS.getValue();
        }

        errorDescription = exception.getMessage();

        return SystemLogRequestDto.builder()
                .payment_id(paymentId)
                .error_type(errorType)
                .error_description(errorDescription)
                .build();
    }

    private boolean isDatabaseException(Exception exception) {
        return exception instanceof DataIntegrityViolationException ||
                exception instanceof PersistenceException ||
                exception instanceof JpaSystemException;
    }
}
