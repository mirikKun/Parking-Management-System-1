package com.kpi.parking.service;

import com.kpi.parking.domain.Vehicle;
import com.kpi.parking.exception.EntityNotFoundException;
import com.kpi.parking.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    public Optional<Vehicle> getById(int id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void update(Vehicle vehicle) {
        verifyVehiclePresent(vehicle.getId());
        vehicleRepository.save(vehicle);
    }

    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }

    private void verifyVehiclePresent(int id) {
        vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Vehicle with id %d is not present", id)));
    }

}
