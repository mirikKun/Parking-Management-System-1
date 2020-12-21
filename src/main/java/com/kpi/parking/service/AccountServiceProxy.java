package com.kpi.parking.service;

import com.kpi.parking.domain.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceProxy implements AccountProcessor {

    private final AccountService accountService;

    public AccountServiceProxy(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Optional<Account> getById(int id) {
        Optional<Account> account = accountService.getById(id);
        account.ifPresent(acc -> acc.setPassword("*****"));
        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = accountService.getAll();
        accounts.forEach(account -> account.setPassword("*****"));
        return accounts;
    }

    @Override
    public void save(Account account) {
        String encryptedPassword = encryptPassword(account.getPassword(), 6);
        account.setPassword(encryptedPassword);
        accountService.save(account);
    }

    @Override
    public void update(Account account) {
        String encryptedPassword = encryptPassword(account.getPassword(), 6);
        account.setPassword(encryptedPassword);
        accountService.update(account);
    }

    @Override
    public void delete(int id) {
        accountService.delete(id);
    }

    private String encryptPassword(String msg, int shift) {
        StringBuilder string = new StringBuilder();
        int len = msg.length();
        for(int x = 0; x < len; x++){
            char c = (char)(msg.charAt(x) + shift);
            if (c > 'z')
                string.append((char) (msg.charAt(x) - (91 - shift)));
            else
                string.append((char) (msg.charAt(x) + shift));
        }
        return string.toString();
    }
}
