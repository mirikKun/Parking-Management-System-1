package com.kpi.parking.service;

import com.kpi.parking.domain.Spot;
import com.kpi.parking.repository.SpotRepository;
import com.kpi.parking.domain.Ticket;

import com.kpi.parking.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SpotService {

    private final SpotRepository spotRepository;

    public SpotService(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }


    public Optional<Spot> getById(int id) {
        return spotRepository.findById(id);
    }

    public List<Spot> getAll() {
        return spotRepository.findAll();
    }

    public void save(Spot spot) {
        spotRepository.save(spot);
    }

    public void update(Spot spot) {
        verifySpotPresent(spot.getId());
        spotRepository.save(spot);
    }

    public void delete(int id) {
        spotRepository.deleteById(id);
    }

    private void verifySpotPresent(int id) {
        spotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Spot with id %d is not present", id)));
    }

}
