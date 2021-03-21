package com.app.service;

import com.app.domain.Account;
import com.app.exception.NotEnoughCashException;
import com.app.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void putCash(BigDecimal amount, Account account) {
        account.setCash(account.getCash().add(amount));
        saveAccount(account);
    }

    public void takeCash(BigDecimal amount, Account account) throws NotEnoughCashException {
        BigDecimal cash = account.getCash();
        if (cash.compareTo(amount) < 0) {
            throw new NotEnoughCashException();
        }
        account.setCash(cash.subtract(amount));
        saveAccount(account);
    }

}
