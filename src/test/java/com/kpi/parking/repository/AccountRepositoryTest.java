package com.kpi.parking.repository;

import com.kpi.parking.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.kpi.parking.TestData.retrievedAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findByUsername() {
        Account actualAccount = accountRepository.findByUsername("username").orElse(null);

        assertEquals(retrievedAccount, actualAccount);
    }
}