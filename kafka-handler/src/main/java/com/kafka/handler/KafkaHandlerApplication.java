package com.kafka.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.payment.processor", "com.kafka.handler"})
public class KafkaHandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaHandlerApplication.class, args);
    }
}
