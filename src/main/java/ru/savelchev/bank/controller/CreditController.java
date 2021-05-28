package ru.savelchev.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.savelchev.bank.model.Bank;
import ru.savelchev.bank.model.Credit;
import ru.savelchev.bank.service.CreditService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/credits")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/")
    public String listCredits(Model model) {
        List<Credit> credits = creditService.findAll();
        model.addAttribute("credits", credits);
        return "credits/credits";
    }

    @GetMapping("/new")
    public String newCredit(@ModelAttribute("credit") Credit credit, Model model) {
        model.addAttribute("path", "new");
        model.addAttribute("name", "Новый кредит");
        model.addAttribute("nameButton", "Добавить");
        return "credits/new";
    }

    @PostMapping("/new")
    public String createCredit(@ModelAttribute("credit") @Valid Credit credit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "new");
            model.addAttribute("name", "Новый кредит");
            model.addAttribute("nameButton", "Добавить");
            return "credits/new";
        }
        creditService.save(credit);
        return "redirect:/credits/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCredit(@PathVariable("id") UUID id) {
        creditService.deleteById(id);
        return "redirect:/credits/";
    }

    @GetMapping("/update/{id}")
    public String updateCreditForm(@PathVariable("id") UUID id, Model model) {
        Credit credit = creditService.findById(id);
        model.addAttribute("credit", credit);
        model.addAttribute("path", "update");
        model.addAttribute("name", "Редактирование кредита");
        model.addAttribute("nameButton", "Изменить");
        return "credits/new";
    }

    @PostMapping("/update")
    public String updateCredit(@ModelAttribute("credit") @Valid Credit credit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("credit", credit);
            model.addAttribute("path", "update");
            model.addAttribute("name", "Редактирование кредита");
            model.addAttribute("nameButton", "Изменить");
            return "credits/new";
        }
        creditService.save(credit);
        return "redirect:/credits/";
    }

    @GetMapping("/search")
    public String searchCredit(@RequestParam("searchName") String searchName, Model model) {
        List<Credit> credits = creditService.findByName(searchName);
        model.addAttribute("credits",credits);
        return "credits/credits";
    }
}
