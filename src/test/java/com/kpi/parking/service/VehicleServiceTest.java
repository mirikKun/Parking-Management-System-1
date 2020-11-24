package com.kpi.parking.service;

import com.kpi.parking.domain.Vehicle;
import com.kpi.parking.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void givenIdOfTheFirstVehicle_whenGetById_thenReturnedVehicleWithGivenId() {
        Optional<Vehicle> expectedVehicle = Optional.of(new Vehicle(1, "car"));
        when(vehicleRepository.findById(1)).thenReturn(expectedVehicle);

        Optional<Vehicle> actualVehicle = vehicleService.getById(1);

        verify(vehicleRepository, times(1)).findById(1);
        assertEquals(expectedVehicle, actualVehicle);
    }

    @Test
    void getAll() {
        List<Vehicle> expectedVehicles = singletonList(new Vehicle(1, "car"));
        when(vehicleRepository.findAll()).thenReturn(expectedVehicles);

        List<Vehicle> actualVehicles = vehicleService.getAll();

        verify(vehicleRepository, times(1)).findAll();
        assertEquals(expectedVehicles, actualVehicles);
    }

    @Test
    void save() {
        Vehicle vehicle = new Vehicle(1, "car");
        vehicleService.save(vehicle);

        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void update() {
        Vehicle vehicle = new Vehicle(1, "car");
        when(vehicleRepository.findById(1)).thenReturn(Optional.of(vehicle));
        vehicleService.update(vehicle);

        verify(vehicleRepository, times(1)).save(vehicle);
    }
}