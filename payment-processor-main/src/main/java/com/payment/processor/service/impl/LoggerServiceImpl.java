package com.payment.processor.service.impl;

import com.payment.processor.mapper.LoggerServiceMapper;
import com.payment.processor.model.Account;
import com.payment.processor.model.Payment;
import com.payment.processor.model.dto.SystemLogRequestDto;
import com.payment.processor.model.dto.PaymentRequestDto;
import com.payment.processor.feign.client.PaymentClient;
import com.payment.processor.service.AccountService;
import com.payment.processor.service.LoggerService;
import com.payment.processor.service.PaymentService;
import com.payment.processor.util.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoggerServiceImpl implements LoggerService {
    private final PaymentClient client;
    private final PaymentService paymentService;
    private final AccountService accountService;
    private final LoggerServiceMapper mapper;

    @Override
    public void logErrorDetails(SystemLogRequestDto requestDto) {
        client.log(requestDto);
    }

    @Override
    public void logPaymentDetails(PaymentRequestDto requestDto) {

        Account account = saveAccount(requestDto);

        savePayment(requestDto, account);

    }

    private void savePayment(PaymentRequestDto requestDto, Account account) {
        Payment payment = mapper.toPayment(requestDto);
        payment.setAccount(account);
        paymentService.savePayment(payment);
    }

    private Account saveAccount(PaymentRequestDto requestDto) {
        try {
            Account account = fetchAccountOrThrow(requestDto.getAccountId());
            account.setLastPaymentDate(requestDto.getCreatedOn());
            return accountService.saveAccount(account);
        } catch (ResourceNotFoundException e) {
            log.error("Account not found: ", e);
            logErrorDetails(requestDto.getPaymentId(), e.getMessage(), ErrorType.DATABASE);
            return null;
        }
    }

    private void logErrorDetails(String paymentId, String errorMessage, ErrorType errorType) {
        SystemLogRequestDto logRequestDto = SystemLogRequestDto.builder()
                .payment_id(paymentId)
                .error_description(errorMessage)
                .error_type(errorType.getValue())
                .build();
        this.logErrorDetails(logRequestDto);
    }

    private Account fetchAccountOrThrow(Integer accountId) {
        return accountService.findByAccountId(accountId);
    }
}
