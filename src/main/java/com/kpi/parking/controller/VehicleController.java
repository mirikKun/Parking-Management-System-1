package com.kpi.parking.controller;

import com.kpi.parking.domain.Vehicle;
import com.kpi.parking.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("vehicles", vehicleService.getAll());
        return "vehicles/vehicles";
    }

    @GetMapping("/new")
    public String redirectToSaveForm(Vehicle vehicleModel) {
        return "vehicles/new";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Vehicle> vehicle = vehicleService.getById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
        } else {
            redirectAttributes.addFlashAttribute("error", "Vehicle is not present");
        }
        return "vehicles/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/vehicles/new";
        }
        vehicleService.save(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        vehicleService.delete(id);
        return "redirect:/vehicles";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/vehicles/edit";
        }
        vehicleService.update(vehicle);
        return "redirect:/vehicles";
    }
}
