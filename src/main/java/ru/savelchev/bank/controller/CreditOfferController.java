package ru.savelchev.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.savelchev.bank.model.*;
import ru.savelchev.bank.service.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("credit-offers")
public class CreditOfferController {

    private final CreditOfferService creditOfferService;
    private final BankService bankService;
    private final PaymentScheduleService paymentScheduleService;

    @Autowired
    public CreditOfferController(CreditOfferService creditOfferService, BankService bankService, PaymentScheduleService paymentScheduleService) {
        this.creditOfferService = creditOfferService;
        this.bankService = bankService;
        this.paymentScheduleService = paymentScheduleService;
    }

    @GetMapping("/new")
    public String newCreditOffer(@ModelAttribute("creditOffer") CreditOffer creditOffer, Model model) {
        model.addAttribute("path", "new");
        model.addAttribute("name", "Новое кредитное предложение");
        model.addAttribute("nameButton", "Оформить");
        return "credit-offers/new";
    }

    @PostMapping("/new")
    public String createCreditOffer(@ModelAttribute("creditOffer") @Valid CreditOffer creditOffer, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "new");
            model.addAttribute("name", "Новое кредитное предложение");
            model.addAttribute("nameButton", "Оформить");
            return "credit-offers/new";
        }
        creditOfferService.save(creditOffer);
        Bank bank = new Bank();
        bank.setClient(creditOffer.getClient());
        bank.setCredit(creditOffer.getCredit());
        bank.setCreditOffer(creditOffer);
        bankService.save(bank);
        redirectAttributes.addFlashAttribute(creditOffer);
        return "redirect:/payment-schedule/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCreditOffer(@PathVariable("id") UUID id) {
        creditOfferService.deleteById(id);
        return "redirect:/bank/";
    }

    @GetMapping("/update")
    public String updateCreditOfferForm(@ModelAttribute("creditOffer") CreditOffer creditOffer, Model model) {
        model.addAttribute("path", "update");
        model.addAttribute("name", "Изменение кредитного предложения");
        model.addAttribute("nameButton", "Оформить");
        return "credit-offers/new";
    }

    @PostMapping("/update")
    public String updateCreditOffer(@ModelAttribute("creditOffer") @Valid CreditOffer creditOffer, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "update");
            model.addAttribute("name", "Изменение кредитного предложения");
            model.addAttribute("nameButton", "Оформить");
        }
        creditOfferService.save(creditOffer);
        List<PaymentSchedule> paymentScheduleList = paymentScheduleService.findByCreditOfferOrderByDate(creditOffer);
        for (PaymentSchedule paymentSchedule : paymentScheduleList) {
            paymentScheduleService.deleteById(paymentSchedule.getId());
        }
        redirectAttributes.addFlashAttribute(creditOffer);
        return "redirect:/payment-schedule/";
    }

}
