package com.kpi.parking.repository;

import com.kpi.parking.domain.Floor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Integer> {

    List<Floor> findAll();

}
