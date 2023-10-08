package com.payment.logger.mapper;

import com.payment.logger.model.Payment;
import com.payment.logger.model.dto.PaymentRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoggerServiceMapper extends BaseMapper {
    Payment toPayment(PaymentRequestDto requestDto);
}
