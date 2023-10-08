package com.payment.processor.feign.client;

import com.payment.processor.feign.config.FeignConfig;
import com.payment.processor.model.dto.PaymentRequestDto;
import com.payment.processor.model.dto.PaymentResponseDto;
import com.payment.processor.model.dto.SystemLogRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@FeignClient(name = "paymentClient", url = "http://localhost:9000", configuration = FeignConfig.class)
public interface PaymentClient {

    @PostMapping("/payment")
    ResponseEntity<PaymentResponseDto> validate(@RequestBody PaymentRequestDto requestDto);

    @PostMapping("/log")
    ResponseEntity<PaymentResponseDto> log(@RequestBody SystemLogRequestDto requestDto);
}
