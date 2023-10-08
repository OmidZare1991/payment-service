package com.payment.processor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wefox")
@Setter
@Getter
public class ConfigProperties {
    private Long redisTimeToLiveMinutes;
}