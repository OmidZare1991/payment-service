package com.kafka.handler.kafka.mapper;

import com.payment.processor.dto.PaymentDto;
import com.payment.processor.dto.PaymentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface KafkaHandlerMapper {
    @Mapping(target = "createdOn",expression = "java(getDate())")
    PaymentRequestDto toPaymentRequestDto(PaymentDto paymentDto);

    @Named("getDate")
    default Timestamp getDate(){
        return new Timestamp(Instant.now().toEpochMilli());
    }
}
