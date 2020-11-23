package com.kpi.parking.controller;

import com.kpi.parking.domain.Floor;
import com.kpi.parking.service.FloorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/floors")
public class FloorController {

    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("floors", floorService.getAll());
        return "floors/floors";
    }

    @GetMapping("/new")
    public String redirectToSaveForm(Floor floorModel) {
        return "floors/new";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Floor> floor = floorService.getById(id);
        if (floor.isPresent()) {
            model.addAttribute("floor", floor.get());
        } else {
            redirectAttributes.addFlashAttribute("error", "Floor is not present");
        }
        return "floors/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("floor") Floor floor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/floors/new";
        }
        floorService.save(floor);
        return "redirect:/floors";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        floorService.delete(id);
        return "redirect:/floors";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("floor") Floor floor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/floors/edit";
        }
        floorService.update(floor);
        return "redirect:/floors";
    }
}
