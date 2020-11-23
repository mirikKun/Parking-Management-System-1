package com.kpi.parking.repository;

import com.kpi.parking.domain.Admin;
import com.kpi.parking.domain.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer>{

    List<Admin> findAll();

    Optional<Admin> findByEmail(String email);

    Optional<Admin> findByPhone(String phone);
}
