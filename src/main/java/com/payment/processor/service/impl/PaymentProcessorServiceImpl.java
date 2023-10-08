package com.payment.processor.service.impl;

import com.payment.processor.exception.BadRequestException;
import com.payment.processor.exception.PaymentNotFoundException;
import com.payment.processor.model.dto.PaymentRequestDto;
import com.payment.processor.model.dto.SystemLogRequestDto;
import com.payment.processor.service.LoggerService;
import com.payment.processor.service.PaymentProcessorService;
import com.payment.processor.service.PaymentValidationService;
import com.payment.processor.util.ErrorType;
import com.payment.processor.util.PaymentType;
import feign.RetryableException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessorServiceImpl implements PaymentProcessorService {
    private final PaymentValidationService validationService;
    private final LoggerService loggerService;

    @Override
    public void processPayments(PaymentRequestDto paymentDto) {
        String paymentId = paymentDto.getPaymentId();
        try {
            boolean isValidated = false;

            if (PaymentType.ONLINE.name.equals(paymentDto.getPaymentType())) {
                isValidated = validationService.validatePaymentDetails(paymentDto);
            }

            if (isValidated || PaymentType.OFFLINE.name.equals(paymentDto.getPaymentType())) {
                loggerService.logPaymentDetails(paymentDto);
            }

        } catch (Exception e) {
            SystemLogRequestDto systemLogRequest = createSystemLogRequest(e, paymentId);
            loggerService.logErrorDetails(systemLogRequest);
        }
    }

    private SystemLogRequestDto createSystemLogRequest(Exception exception, String paymentId) {
        String errorType;
        String errorDescription;

        if (isNetworkException(exception)) {
            errorType = ErrorType.NETWORK.getValue();
        } else if (isDatabaseException(exception)) {
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

    private boolean isNetworkException(Exception exception) {
        return exception instanceof PaymentNotFoundException ||
                exception instanceof BadRequestException ||
                exception instanceof RetryableException;
    }

    private boolean isDatabaseException(Exception exception) {
        return exception instanceof DataIntegrityViolationException ||
                exception instanceof PersistenceException ||
                exception instanceof JpaSystemException;
    }
}
