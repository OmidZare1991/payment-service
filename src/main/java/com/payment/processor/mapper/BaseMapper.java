package com.payment.processor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    @Named("toDate")
    default String toDate(Long dateValue) {
        Date date = new Date(dateValue);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        return dateFormat.format(date);
    }
}
