package com.payment.processor.feign.decoder;

import com.payment.processor.exception.BadRequestException;
import com.payment.processor.exception.PaymentNotFoundException;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class PaymentErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = FeignException.errorStatus(methodKey, response);
        int status = response.status();

        switch (status){
            case 400:
                return new BadRequestException();
            case 404:
                return new PaymentNotFoundException("Product not found");
            default:
                if (status >= 500) {
                    return new RetryableException(
                            response.status(),
                            exception.getMessage(),
                            response.request().httpMethod(),
                            exception,
                            null,
                            response.request());
                }
        }
        return exception;

    }
}