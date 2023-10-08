package com.payment.processor.service;

import com.payment.processor.model.Account;

public interface AccountService {
    Account findByAccountId(Integer id);
    Account saveAccount(Account account);
}
