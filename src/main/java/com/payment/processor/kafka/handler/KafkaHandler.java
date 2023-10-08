package com.payment.processor.kafka.handler;

import com.google.gson.Gson;
import com.payment.processor.kafka.mapper.KafkaHandlerMapper;
import com.payment.processor.model.dto.PaymentDto;
import com.payment.processor.service.PaymentProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaHandler {
    private final PaymentProcessorService processorService;
    private final KafkaHandlerMapper mapper;
    private final Gson gson;

    @KafkaListener(topics = {"online", "offline"})
    public void consumeMessage(String payload, Acknowledgment acknowledgment) {
        try {
            PaymentDto paymentDto = gson.fromJson(payload, PaymentDto.class);
            processorService.processPayments(mapper.toPaymentRequestDto(paymentDto));
            acknowledgment.acknowledge();
        }catch (Exception e){
            log.error("failed to process the request with error: ",e);
        }
    }

}
