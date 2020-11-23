package com.kpi.parking.service;

import com.kpi.parking.domain.Admin;
import com.kpi.parking.repository.AdminRepository;
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
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    void givenIdOfTheFirstAdmin_whenGetById_thenReturnedAdminWithGivenId() {
        Optional<Admin> expectedAdmin = Optional.of(new Admin(1, "Bob Marley", "Khreschatyk St, 14, Kyiv, 01001", "marley@gmail.com", "+380505050505", 1));
        when(adminRepository.findById(1)).thenReturn(expectedAdmin);

        Optional<Admin> actualAdmin = adminService.getById(1);

        verify(adminRepository, times(1)).findById(1);
        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    void getAll() {
        List<Admin> expectedAdmins = singletonList(new Admin(1, "Bob Marley", "Khreschatyk St, 14, Kyiv, 01001", "marley@gmail.com", "+380505050505", 1));
        when(adminRepository.findAll()).thenReturn(expectedAdmins);

        List<Admin> actualAdmins = adminService.getAll();

        verify(adminRepository, times(1)).findAll();
        assertEquals(expectedAdmins, actualAdmins);
    }

    @Test
    void save() {
        Admin admin = new Admin(1, "Bob Marley", "Khreschatyk St, 14, Kyiv, 01001", "marley@gmail.com", "+380505050505", 1);
        when(adminRepository.findByEmail("marley@gmail.com")).thenReturn(Optional.empty());
        when(adminRepository.findByPhone("+380505050505")).thenReturn(Optional.empty());
        adminService.save(admin);

        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    void update() {
        Admin admin = new Admin(1, "Bob Marley", "Khreschatyk St, 14, Kyiv, 01001", "marley@gmail.com", "+380505050505", 1);
        when(adminRepository.findById(1)).thenReturn(Optional.of(admin));
        when(adminRepository.findByEmail("marley@gmail.com")).thenReturn(Optional.empty());

        adminService.update(admin);

        verify(adminRepository, times(1)).save(admin);
    }
}