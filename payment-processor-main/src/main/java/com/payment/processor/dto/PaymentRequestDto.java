package com.payment.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
    private String paymentId;
    private Integer accountId;
    private String paymentType;
    private String creditCard;
    private BigDecimal amount;
    private Timestamp createdOn;
}
