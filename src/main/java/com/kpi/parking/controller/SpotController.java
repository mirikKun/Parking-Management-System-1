package com.kpi.parking.controller;

import com.kpi.parking.domain.Spot;
import com.kpi.parking.service.SpotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/spots")
public class SpotController {

    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("spots", spotService.getAll());
        return "spots/spots";
    }

    @GetMapping("/new")
    public String redirectToSaveForm(Spot spotModel) {
        return "spots/new";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Spot> spot = spotService.getById(id);
        if (spot.isPresent()) {
            model.addAttribute("spot", spot.get());
        } else {
            redirectAttributes.addFlashAttribute("error", "Spot is not present");
        }
        return "spots/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("spot") Spot spot, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/spots/new";
        }
        spotService.save(spot);
        return "redirect:/spots";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        spotService.delete(id);
        return "redirect:/spots";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("spot") Spot spot, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/spots/edit";
        }
        spotService.update(spot);
        return "redirect:/spots";
    }
}
