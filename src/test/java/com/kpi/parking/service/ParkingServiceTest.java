package com.kpi.parking.service;

import com.kpi.parking.domain.Parking;
import com.kpi.parking.repository.ParkingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ParkingServiceTest {

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    private ParkingService parkingService;

    @Test
    void givenIdOfTheFirstParking_whenGetById_thenReturnedParkingWithGivenId() {
        Optional<Parking> expectedParking = Optional.of(new Parking(1, "Peremohy Ave, 37, Kyiv, 03056"));
        when(parkingRepository.findById(1)).thenReturn(expectedParking);

        Optional<Parking> actualParking = parkingRepository.findById(1);

        verify(parkingRepository, times(1)).findById(1);
        assertEquals(expectedParking, actualParking);
    }

    @Test
    void getAll() {
        List<Parking> expectedParkings = singletonList(new Parking(1, "Peremohy Ave, 37, Kyiv, 03056"));
        when(parkingRepository.findAll()).thenReturn(expectedParkings);

        List<Parking> actualParkings = parkingService.getAll();

        verify(parkingRepository, times(1)).findAll();
        assertEquals(expectedParkings, actualParkings);
    }

    @Test
    void save() {
        Parking parking = new Parking(1, "Peremohy Ave, 37, Kyiv, 03056");
        parkingService.save(parking);

        verify(parkingRepository, times(1)).save(parking);
    }

    @Test
    void update() {
        Parking parking = new Parking(1, "Peremohy Ave, 37, Kyiv, 03056");
        when(parkingRepository.findById(1)).thenReturn(Optional.of(parking));

        parkingService.update(parking);

        verify(parkingRepository, times(1)).save(parking);
    }
}