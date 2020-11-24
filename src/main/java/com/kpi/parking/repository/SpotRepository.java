package com.kpi.parking.repository;

import com.kpi.parking.domain.Account;
import com.kpi.parking.domain.Spot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface SpotRepository extends CrudRepository<Spot, Integer> {

    List<Spot> findAll();
}
