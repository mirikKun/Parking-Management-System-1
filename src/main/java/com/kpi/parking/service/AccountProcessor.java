package com.kpi.parking.service;

import com.kpi.parking.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountProcessor {

    Optional<Account> getById(int id);

    List<Account> getAll();

    void save(Account account);

    void update(Account account);

    void delete(int id);
}
