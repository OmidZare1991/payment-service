package com.payment.processor.service.impl;

import com.payment.processor.strategy.PaymentProcessorStrategy;
import com.payment.processor.util.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentProcessorContext {
    private final List<PaymentProcessorStrategy> processorStrategies;

    PaymentProcessorStrategy getStrategy(PaymentType type){
        return processorStrategies
                .stream()
                .filter(strategyServices -> strategyServices.getType().equals(type))
                .findFirst()
                .orElseThrow();
    }
}
