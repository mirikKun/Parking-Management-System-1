package com.kpi.parking.service;


import com.kpi.parking.domain.Floor;
import com.kpi.parking.repository.FloorRepository;
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
class FloorServiceTest {
    @Mock
    private FloorRepository floorRepository;

    @InjectMocks
    private FloorService floorService;

    @Test
    void givenIdOfTheFirstFloor_whenGetById_thenReturnedAdminWithGivenId() {
        Optional<Floor> expectedFloor = Optional.of(new Floor(1, 1, 40, 1));
        when(floorRepository.findById(1)).thenReturn(expectedFloor);

        Optional<Floor> actualFloor = floorService.getById(1);

        verify(floorRepository, times(1)).findById(1);
        assertEquals(expectedFloor, actualFloor);
    }

    @Test
    void getAll() {
        List<Floor> expectedFloors = singletonList(new Floor(1, 1, 40, 1));
        when(floorRepository.findAll()).thenReturn(expectedFloors);

        List<Floor> actualFloors = floorService.getAll();

        verify(floorRepository, times(1)).findAll();
        assertEquals(expectedFloors, actualFloors);
    }

    @Test
    void save() {
        Floor floor = new Floor(1, 1, 40, 1);
        floorService.save(floor);

        verify(floorRepository, times(1)).save(floor);
    }

    @Test
    void update() {
        Floor floor = new Floor(1, 1, 40, 1);
        when(floorRepository.findById(1)).thenReturn(Optional.of(floor));

        floorService.update(floor);

        verify(floorRepository, times(1)).save(floor);
    }
}