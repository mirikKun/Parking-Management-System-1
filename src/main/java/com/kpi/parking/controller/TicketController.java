package com.kpi.parking.controller;

import com.kpi.parking.domain.Receipt;
import com.kpi.parking.domain.Ticket;
import com.kpi.parking.service.ReceiptService;
import com.kpi.parking.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ReceiptService receiptService;

    public TicketController(TicketService ticketService, ReceiptService receiptService) {
        this.ticketService = ticketService;
        this.receiptService = receiptService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("tickets", ticketService.getAll());
        return "tickets/tickets";
    }

    @GetMapping("/new")
    public String redirectToSaveForm(Ticket ticketModel) {
        return "tickets/new";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Ticket> ticket = ticketService.getById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
        } else {
            redirectAttributes.addFlashAttribute("error", "Ticket is not present");
        }
        return "tickets/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/tickets/new";
        }
        ticketService.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        ticketService.delete(id);
        return "redirect:/tickets";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/tickets/edit";
        }
        ticketService.update(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("receipt/{id}")
    public String pay(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Ticket> ticket = ticketService.getById(id);
        if (ticket.isPresent()) {
            Receipt receipt = receiptService.check(ticket.get(), LocalDateTime.now());
            model.addAttribute("receipt", receipt);
        } else {
            redirectAttributes.addFlashAttribute("error", "Ticket is not");
        }
        return "tickets/receipt";
    }

    @GetMapping("/paidsuccessful")
    public String success() {
        return "tickets/paidsuccessful";
    }
}
