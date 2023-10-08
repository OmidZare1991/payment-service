package com.payment.processor.mapper;

import com.payment.processor.model.Payment;
import com.payment.processor.model.dto.PaymentRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoggerServiceMapper extends BaseMapper {
    Payment toPayment(PaymentRequestDto requestDto);
}
