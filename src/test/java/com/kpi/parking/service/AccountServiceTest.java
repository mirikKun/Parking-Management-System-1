package com.kpi.parking.service;

import com.kpi.parking.domain.Account;
import com.kpi.parking.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void givenIdOfTheFirstAccount_whenGetById_thenReturnedAccountWithGivenId() {
        Optional<Account> expectedAccount = Optional.of(new Account(1, "username", "password"));
        when(accountRepository.findById(1)).thenReturn(expectedAccount);

        Optional<Account> actualAccount = accountService.getById(1);

        verify(accountRepository, times(1)).findById(1);
        assertEquals(expectedAccount, actualAccount);
    }

    @Test
    void getAll() {
        List<Account> expectedAccounts = singletonList(new Account(1, "username", "password"));
        when(accountRepository.findAll()).thenReturn(expectedAccounts);

        List<Account> actualAccounts = accountService.getAll();

        verify(accountRepository, times(1)).findAll();
        assertEquals(expectedAccounts, actualAccounts);
    }

    @Test
    void save() {
        Account account = new Account(1, "username", "password");
        when(accountRepository.findByUsername("username")).thenReturn(Optional.empty());

        accountService.save(account);

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void update() {
        Account account = new Account(1, "username", "password");
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(accountRepository.findByUsername("username")).thenReturn(Optional.empty());

        accountService.update(account);

        verify(accountRepository, times(1)).save(account);
    }
}