package com.kpi.parking.service;

import com.kpi.parking.domain.Admin;
import com.kpi.parking.exception.EmailNotUniqueException;
import com.kpi.parking.exception.EntityNotFoundException;
import com.kpi.parking.exception.PhoneNotUniqueException;
import com.kpi.parking.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminDao, AdminRepository adminRepository) { this.adminRepository = adminRepository; }

    public Optional<Admin> getById(int id) {
        return adminRepository.findById(id);
    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public void save(Admin admin) {
        verifyAdminUnique(admin);
        adminRepository.save(admin);
    }

    public void update(Admin admin) {
        verifyAdminUnique(admin);
        verifyAdminPresent(admin.getId());
        adminRepository.save(admin);
    }

    public void delete(int id) {
        adminRepository.deleteById(id);
    }

    private void verifyAdminPresent(int id) {
        adminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Admin with id %d is not present", id)));
    }

    private void verifyAdminUnique(Admin admin) {
        adminRepository.findByEmail(admin.getEmail()).ifPresent(adminWithSameEmail -> {
            if (admin.getId() != adminWithSameEmail.getId()) {
                throw new EmailNotUniqueException(String.format("Admin with email %s already exist", admin.getEmail()));
            }
        });
        adminRepository.findByPhone(admin.getPhone()).ifPresent(adminWithSamePhone -> {
            if (admin.getId() != adminWithSamePhone.getId()) {
                throw new PhoneNotUniqueException(String.format("Admin with phone %s already exist", admin.getEmail()));
            }
        });
    }
}
