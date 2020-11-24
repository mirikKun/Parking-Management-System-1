package com.kpi.parking.service;

import com.kpi.parking.domain.Spot;
import com.kpi.parking.repository.SpotRepository;
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
class SpotServiceTest {


    @Mock
    private SpotRepository SpotRepository;

    @InjectMocks
    private SpotService SpotService;

    @Test
    void givenIdOfTheFirstSpot_whenGetById_thenReturnedSpotWithGivenId() {
        Optional<Spot> expectedSpot = Optional.of(new Spot(1, true,"Compact", 1));
        when(SpotRepository.findById(1)).thenReturn(expectedSpot);

        Optional<Spot> actualSpot = SpotService.getById(1);

        verify(SpotRepository, times(1)).findById(1);
        assertEquals(expectedSpot, actualSpot);
    }

    @Test
    void getAll() {
        List<Spot> expectedSpots = singletonList(new Spot(1, true,"Compact", 1));
        when(SpotRepository.findAll()).thenReturn(expectedSpots);

        List<Spot> actualSpots = SpotService.getAll();

        verify(SpotRepository, times(1)).findAll();
        assertEquals(expectedSpots, actualSpots);
    }

    @Test
    void save() {
        Spot Spot = new Spot(1, true,"Compact", 1);

        SpotService.save(Spot);

        verify(SpotRepository, times(1)).save(Spot);
    }

    @Test
    void update() {
        Spot Spot = new Spot(1, true,"Compact", 1);
        when(SpotRepository.findById(1)).thenReturn(Optional.of(Spot));

        SpotService.update(Spot);

        verify(SpotRepository, times(1)).save(Spot);
    }
}