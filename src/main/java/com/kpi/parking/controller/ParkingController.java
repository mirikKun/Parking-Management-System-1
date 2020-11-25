package com.kpi.parking.controller;

import com.kpi.parking.domain.Parking;
import com.kpi.parking.service.ParkingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/parkings")
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("parkings", parkingService.getAll());
        return "parkings/parkings";
    }

    @GetMapping("/new")
    public String redirectToSaveForm(Parking parkingModel) {
        return "parkings/new";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Parking> parking = parkingService.getById(id);
        if (parking.isPresent()) {
            model.addAttribute("parking", parking.get());
        } else {
            redirectAttributes.addFlashAttribute("error", "Parking is not present");
        }
        return "parkings/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("parking") Parking parking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/parkings/new";
        }
        parkingService.save(parking);
        return "redirect:/parkings";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        parkingService.delete(id);
        return "redirect:/parkings";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("parking") Parking parking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/parkings/edit";
        }
        parkingService.update(parking);
        return "redirect:/parkings";
    }
}
