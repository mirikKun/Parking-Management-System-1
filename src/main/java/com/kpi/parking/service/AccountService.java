package com.kpi.parking.service;

import com.kpi.parking.domain.Account;
import com.kpi.parking.exception.EntityNotFoundException;
import com.kpi.parking.exception.UsernameNotUniqueException;
import com.kpi.parking.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Optional<Account> getById(int id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public void save(Account account) {
        verifyAccountUnique(account);
        accountRepository.save(account);
    }

    public void update(Account account) {
        verifyAccountUnique(account);
        verifyAccountPresent(account.getId());
        accountRepository.save(account);
    }

    public void delete(int id) {
        accountRepository.deleteById(id);
    }

    private void verifyAccountPresent(int id) {
        accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Audience with id %d is not present", id)));
    }

    private void verifyAccountUnique(Account account) {
        accountRepository.findByUsername(account.getUsername()).ifPresent(accountWithSameUsername -> {
            if (account.getId() != accountWithSameUsername.getId()) {
                throw new UsernameNotUniqueException(String.format("Account with username %s already exist", account.getUsername()));
            }
        });
    }
}
