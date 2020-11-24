package com.kpi.parking.repository;

import com.kpi.parking.domain.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends CrudRepository<Parking, Integer> {

    List<Parking> findAll();

}
