package com.payment.processor.strategy;

import com.payment.processor.exception.BadRequestException;
import com.payment.processor.exception.PaymentNotFoundException;
import com.payment.processor.dto.PaymentRequestDto;
import com.payment.processor.dto.SystemLogRequestDto;
import com.payment.processor.service.PaymentValidationService;
import com.payment.processor.util.ErrorType;
import com.payment.processor.util.PaymentType;
import feign.RetryableException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OnlinePaymentProcessorStrategy implements PaymentProcessorStrategy {

    private final PaymentValidationService validationService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public PaymentType getType() {
        return PaymentType.ONLINE;
    }

    @Override
    public void processPayments(PaymentRequestDto paymentDto) {
        String paymentId = paymentDto.getPaymentId();
        try {

            boolean isValidated = validationService.validatePaymentDetails(paymentDto);

            if (isValidated) {
                kafkaTemplate.send("processed-payments",paymentDto);
            }

        } catch (Exception e) {
            SystemLogRequestDto systemLogRequest = createSystemLogRequest(e, paymentId);
            kafkaTemplate.send("error-payments",systemLogRequest);
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

        return SystemLogRequestDto.builder().payment_id(paymentId).error_type(errorType).error_description(errorDescription).build();
    }

    private boolean isNetworkException(Exception exception) {
        return exception instanceof PaymentNotFoundException || exception instanceof BadRequestException || exception instanceof RetryableException;
    }

    private boolean isDatabaseException(Exception exception) {
        return exception instanceof DataIntegrityViolationException || exception instanceof PersistenceException || exception instanceof JpaSystemException;
    }
}
