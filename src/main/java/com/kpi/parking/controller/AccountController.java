package com.kpi.parking.controller;

import com.kpi.parking.domain.Account;
import com.kpi.parking.service.AccountService;
import com.kpi.parking.service.AccountServiceProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceProxy accountService;

    public AccountController(AccountServiceProxy accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("accounts", accountService.getAll());
        return "accounts/accounts";
    }

    @GetMapping("/new")
    public String redirectToSaveForm(Account accountModel) {
        return "accounts/new";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Account> account = accountService.getById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
        } else {
            redirectAttributes.addFlashAttribute("error", "Account is not present");
        }
        return "accounts/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/accounts/new";
        }
        accountService.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        accountService.delete(id);
        return "redirect:/accounts";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/accounts/edit";
        }
        accountService.update(account);
        return "redirect:/accounts";
    }
}
