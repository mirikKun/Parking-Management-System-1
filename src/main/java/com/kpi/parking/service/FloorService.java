package com.kpi.parking.service;

import com.kpi.parking.domain.Floor;
import com.kpi.parking.exception.EntityNotFoundException;
import com.kpi.parking.repository.FloorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    private final FloorRepository floorRepository;

    public FloorService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public Optional<Floor> getById(int id) {
        return floorRepository.findById(id);
    }

    public List<Floor> getAll() {
        return floorRepository.findAll();
    }

    public void save(Floor floor) { floorRepository.save(floor); }

    public void update(Floor floor) {
        verifyAdminPresent(floor.getId());
        floorRepository.save(floor);
    }

    public void delete(int id) {
        floorRepository.deleteById(id);
    }

    private void verifyAdminPresent(int id) {
        floorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Floor with id %d is not present", id)));
    }
}
