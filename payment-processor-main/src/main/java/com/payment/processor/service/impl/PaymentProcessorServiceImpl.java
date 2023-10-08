package com.payment.processor.service.impl;

import com.payment.processor.model.dto.PaymentRequestDto;
import com.payment.processor.service.PaymentProcessorService;
import com.payment.processor.util.PaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessorServiceImpl implements PaymentProcessorService {
    private final PaymentProcessorContext processorContext;
    @Override
    public void processPayments(PaymentRequestDto paymentDto, PaymentType type) {
        processorContext.getStrategy(type).processPayments(paymentDto);
    }
}
