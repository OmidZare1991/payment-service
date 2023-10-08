package com.payment.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaymentLoggerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentLoggerServiceApplication.class, args);
    }

}
