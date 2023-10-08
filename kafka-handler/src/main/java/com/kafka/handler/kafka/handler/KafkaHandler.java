package com.kafka.handler.kafka.handler;

import com.google.gson.Gson;
import com.kafka.handler.kafka.mapper.KafkaHandlerMapper;

import com.payment.processor.dto.PaymentDto;
import com.payment.processor.dto.PaymentRequestDto;
import com.payment.processor.service.PaymentProcessorService;
import com.payment.processor.util.PaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class KafkaHandler {
    private final PaymentProcessorService processorService;
    private final KafkaHandlerMapper mapper;
    private final Gson gson;
    private final Environment environment;

    @KafkaListener(topics = "${kafka.payment.topic.name}")
    public void consumePaymentMessage(String payload, Acknowledgment acknowledgment) {

        try {
            PaymentDto paymentDto = deserializePayload(payload);
            PaymentType paymentType = getPaymentType();
            processPayment(paymentDto, paymentType);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Failed to process the payment request with error: ", e);
        }

    }

    private PaymentDto deserializePayload(String payload) {
        return gson.fromJson(payload, PaymentDto.class);
    }

    private PaymentType getPaymentType() {
        return PaymentType.getType(environment.getProperty("payment.type"));
    }

    private void processPayment(PaymentDto paymentDto, PaymentType paymentType) {
        PaymentRequestDto requestDto = mapper.toPaymentRequestDto(paymentDto);
        processorService.processPayments(requestDto, paymentType);
    }

}
