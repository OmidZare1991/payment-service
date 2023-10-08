package com.payment.processor.service.impl;

import com.payment.processor.dto.PaymentResponseDto;
import com.payment.processor.dto.PaymentRequestDto;
import com.payment.processor.feign.client.PaymentClient;
import com.payment.processor.service.PaymentValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentValidationServiceImpl implements PaymentValidationService {
    private final PaymentClient client;
    @Override
    public boolean validatePaymentDetails(PaymentRequestDto request) {

        ResponseEntity<PaymentResponseDto> response = this.client.validate(request);

        return response.getStatusCode().is2xxSuccessful();

    }
}
