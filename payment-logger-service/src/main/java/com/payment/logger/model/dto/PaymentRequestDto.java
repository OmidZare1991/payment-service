package com.payment.logger.model.dto;

import com.google.gson.annotations.SerializedName;
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
    @SerializedName("createdOn")
    private Timestamp createdOn;
}
