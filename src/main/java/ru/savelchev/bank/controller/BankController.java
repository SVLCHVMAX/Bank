package ru.savelchev.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.savelchev.bank.model.Bank;
import ru.savelchev.bank.model.Client;
import ru.savelchev.bank.model.Credit;
import ru.savelchev.bank.model.CreditOffer;
import ru.savelchev.bank.service.BankService;
import ru.savelchev.bank.service.ClientService;
import ru.savelchev.bank.service.CreditOfferService;
import ru.savelchev.bank.service.CreditService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;
    private final ClientService clientService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;

    @Autowired
    public BankController(BankService bankService, ClientService clientService, CreditService creditService, CreditOfferService creditOfferService) {
        this.bankService = bankService;
        this.clientService = clientService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
    }

    @GetMapping("/")
    public String listBanks(Model model) {
        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        return "bank/bank";
    }

    @GetMapping("/new")
    public String newBankForm(@ModelAttribute("bank") Bank bank, Model model) {
        List<Client> clients = clientService.findAll();
        List<Credit> credits = creditService.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("credits", credits);
        model.addAttribute("path", "new");
        model.addAttribute("name", "Новое кредитное предложение");
        return "bank/new";
    }

    @PostMapping("/new")
    public String createBank(@ModelAttribute("bank") @Valid Bank bank, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bank/new";
        }
        Client client = clientService.findById(bank.getClient().getId());
        Credit credit = creditService.findById(bank.getCredit().getId());
        CreditOffer creditOffer = new CreditOffer();
        creditOffer.setClient(client);
        creditOffer.setCredit(credit);
        redirectAttributes.addFlashAttribute(creditOffer);
        return "redirect:/credit-offers/new";
    }

    @GetMapping("/update/{id}")
    public String updateBankForm(@PathVariable("id") UUID id, Model model) {
        Bank bank = bankService.findById(id);
        List<Client> clients = clientService.findAll();
        List<Credit> credits = creditService.findAll();
        model.addAttribute("bank", bank);
        model.addAttribute("clients", clients);
        model.addAttribute("credits", credits);
        model.addAttribute("path", "update");
        model.addAttribute("name", "Изменение кредитного предложения");
        return "bank/new";
    }

    @PostMapping("/update")
    public String updateBank(@ModelAttribute("bank") @Valid Bank bank, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bank/new";
        }
        Client client = clientService.findById(bank.getClient().getId());
        Credit credit = creditService.findById(bank.getCredit().getId());
        CreditOffer creditOffer = creditOfferService.findById(bank.getCreditOffer().getId());
        creditOffer.setClient(client);
        creditOffer.setCredit(credit);
        bankService.deleteById(bank.getId());
        bankService.save(bank);
        redirectAttributes.addFlashAttribute(creditOffer);
        return "redirect:/credit-offers/update";
    }

    @GetMapping("/search")
    public String searchBank(@RequestParam("searchName") String searchName, Model model) {
        List<Bank> banks = bankService.findByClientLastNameIgnoreCase(searchName);
        model.addAttribute("banks",banks);
        return "bank/bank";
    }

}
