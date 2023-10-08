package com.kafka.handler.kafka.mapper;

import com.payment.processor.model.dto.PaymentDto;
import com.payment.processor.model.dto.PaymentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface KafkaHandlerMapper {
    @Mapping(target = "createdOn",expression = "java(getDate())")
    PaymentRequestDto toPaymentRequestDto(PaymentDto paymentDto);

    @Named("getDate")
    default Timestamp getDate(){
        return new Timestamp(new Date().getTime());
    }
}
