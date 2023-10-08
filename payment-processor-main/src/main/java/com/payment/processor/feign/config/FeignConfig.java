package com.payment.processor.feign.config;

import com.payment.processor.feign.decoder.PaymentErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	ErrorDecoder errorDecoder() {
		return new PaymentErrorDecoder();
	}

}