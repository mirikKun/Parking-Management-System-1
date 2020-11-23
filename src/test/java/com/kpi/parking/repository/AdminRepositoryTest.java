package com.kpi.parking.repository;

import com.kpi.parking.domain.Admin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.kpi.parking.TestData.retrievedAdmin;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void findByEmail() {
        Admin actualAdmin = adminRepository.findByEmail("marley@gmail.com").orElse(null);

        assertEquals(retrievedAdmin, actualAdmin);
    }

    @Test
    void findByPhone() {
        Admin actualAdmin = adminRepository.findByPhone("+380505050505").orElse(null);

        assertEquals(retrievedAdmin, actualAdmin);
    }
}