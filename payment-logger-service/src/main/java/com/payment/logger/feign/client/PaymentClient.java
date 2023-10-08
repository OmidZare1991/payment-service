package com.payment.logger.feign.client;


import com.payment.logger.model.dto.PaymentResponseDto;
import com.payment.logger.model.dto.SystemLogRequestDto;
import com.payment.logger.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@FeignClient(name = "paymentClient", url = "http://localhost:9000", configuration = FeignConfig.class)
public interface PaymentClient {

    @PostMapping("/log")
    ResponseEntity<PaymentResponseDto> log(@RequestBody SystemLogRequestDto requestDto);
}
