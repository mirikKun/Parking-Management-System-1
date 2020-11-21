package com.kpi.parking.repository;

import com.kpi.parking.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findAll();

    Optional<Account> findByUsername(String username);
}
