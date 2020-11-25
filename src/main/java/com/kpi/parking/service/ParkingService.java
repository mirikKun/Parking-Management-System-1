package com.kpi.parking.service;

import com.kpi.parking.domain.Parking;
import com.kpi.parking.exception.EntityNotFoundException;
import com.kpi.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) { this.parkingRepository = parkingRepository; }

    public Optional<Parking> getById(int id) {
        return parkingRepository.findById(id);
    }

    public List<Parking> getAll() {
        return parkingRepository.findAll();
    }

    public void save(Parking parking) { parkingRepository.save(parking); }

    public void update(Parking parking) {
        verifyParkingPresent(parking.getId());
        parkingRepository.save(parking);
    }

    public void delete(int id) {
        parkingRepository.deleteById(id);
    }

    private void verifyParkingPresent(int id) {
        parkingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Parking with id %d is not present", id)));
    }
}
