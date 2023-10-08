package com.payment.logger.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.logger.model.dto.PaymentRequestDto;
import com.payment.logger.model.dto.SystemLogRequestDto;
import com.payment.logger.service.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoggerHandler {
    private final ObjectMapper objectMapper;
    private final LoggerService loggerService;

    @KafkaListener(topics = "processed-payments",groupId = "logger-service")
    public void consumeProcessedPayments(@Payload String requestDto) throws JsonProcessingException {
        PaymentRequestDto paymentRequestDto = objectMapper.readValue(requestDto, PaymentRequestDto.class);
        loggerService.logPaymentDetails(paymentRequestDto);
    }

    @KafkaListener(topics = "error-payments",groupId = "logger-service")
    public void consumeErrorLogs(@Payload String systemLogRequestDto) throws JsonProcessingException {
        SystemLogRequestDto systemLogRequestDto1 = objectMapper.readValue(systemLogRequestDto, SystemLogRequestDto.class);
        loggerService.logErrorDetails(systemLogRequestDto1);
    }
}

