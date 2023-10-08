package com.payment.logger.service;


import com.payment.logger.model.Account;

public interface AccountService {
    Account findByAccountId(Integer id);
    Account saveAccount(Account account);
}
