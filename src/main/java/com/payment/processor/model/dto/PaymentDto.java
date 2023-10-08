package com.payment.processor.model.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PaymentDto {

    @SerializedName("payment_id")
    private String paymentId;
    @SerializedName("account_id")
    private Long accountId;
    @SerializedName("payment_type")
    private String paymentType;
    @SerializedName("credit_card")
    private String creditCard;
    private BigDecimal amount;
}
