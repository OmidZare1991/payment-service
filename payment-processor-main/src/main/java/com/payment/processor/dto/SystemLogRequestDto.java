package com.payment.processor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemLogRequestDto {
    @JsonProperty("payment_id")
    private String payment_id;
    @JsonProperty("error_type")
    private String error_type;
    @JsonProperty("error_description")
    private String error_description;
}
