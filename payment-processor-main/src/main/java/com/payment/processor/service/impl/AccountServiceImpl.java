package com.payment.processor.service.impl;

import com.payment.processor.model.Account;
import com.payment.processor.repository.AccountRepository;
import com.payment.processor.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    @Cacheable(value = "accountById",key = "#id")
    public Account findByAccountId(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format("Account with id: %s not found",id)));
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}
