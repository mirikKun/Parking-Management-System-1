package com.kpi.parking.controller;

import com.kpi.parking.domain.Admin;
import com.kpi.parking.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("admins", adminService.getAll());
        return "admins/admins";
    }

    @GetMapping("/new")
    public String redirectToSaveForm(Admin adminModel) {
        return "admins/new";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Admin> admin = adminService.getById(id);
        if (admin.isPresent()) {
            model.addAttribute("admin", admin.get());
        } else {
            redirectAttributes.addFlashAttribute("error", "Admin is not present");
        }
        return "admins/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("admin") Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admins/new";
        }
        adminService.save(admin);
        return "redirect:/admins";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        adminService.delete(id);
        return "redirect:/admins";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("admin") Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admins/edit";
        }
        adminService.update(admin);
        return "redirect:/admins";
    }
}
