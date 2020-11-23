package com.kpi.parking.controller;

import com.kpi.parking.domain.Payment;
import com.kpi.parking.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("payments", paymentService.getAll());
        return "payments/payments";
    }

    @GetMapping("/new")
    public String redirectToSaveForm(Payment paymentModel) {
        return "payments/new";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Payment> payment = paymentService.getById(id);
        if (payment.isPresent()) {
            model.addAttribute("payment", payment.get());
        } else {
            redirectAttributes.addFlashAttribute("error", "Payment is not present");
        }
        return "payments/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("payment") Payment payment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/payments/new";
        }
        paymentService.save(payment);
        return "redirect:/payments";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        paymentService.delete(id);
        return "redirect:/payments";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("payment") Payment payment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/payments/edit";
        }
        paymentService.update(payment);
        return "redirect:/payments";
    }
}
