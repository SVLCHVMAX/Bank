package ru.savelchev.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.savelchev.bank.model.Client;
import ru.savelchev.bank.model.Credit;
import ru.savelchev.bank.model.CreditOffer;
import ru.savelchev.bank.model.PaymentSchedule;
import ru.savelchev.bank.service.ClientService;
import ru.savelchev.bank.service.CreditOfferService;
import ru.savelchev.bank.service.CreditService;
import ru.savelchev.bank.service.PaymentScheduleService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("payment-schedule")
public class PaymentScheduleController {

    private final PaymentScheduleService paymentScheduleService;
    private final ClientService clientService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;

    @Autowired
    public PaymentScheduleController(PaymentScheduleService paymentScheduleService, ClientService clientService, CreditService creditService, CreditOfferService creditOfferService) {
        this.paymentScheduleService = paymentScheduleService;
        this.clientService = clientService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
    }

    @GetMapping("/")
    public String getNewPaymentSchedule(@ModelAttribute("creditOffer") CreditOffer creditOffer, Model model) {
        Credit credit = creditService.findById(creditOffer.getCredit().getId());
        Client client = clientService.findById(creditOffer.getClient().getId());
        creditOffer.setCredit(credit);
        creditOffer.setClient(client);
        LocalDate date = LocalDate.now();
        List<PaymentSchedule> paymentScheduleList = paymentScheduleService.calculatePaymentSchedule(creditOffer, date);
        paymentScheduleService.saveAll(paymentScheduleList);
        model.addAttribute("paymentScheduleList", paymentScheduleList);
        return "payment-schedule/payment-schedule";
    }

    @GetMapping("/{id}")
    public String getPaymentSchedule(@PathVariable("id") UUID id, Model model) {
        CreditOffer creditOffer = creditOfferService.findById(id);
        Credit credit = creditService.findById(creditOffer.getCredit().getId());
        Client client = clientService.findById(creditOffer.getClient().getId());
        creditOffer.setCredit(credit);
        creditOffer.setClient(client);
        List<PaymentSchedule> paymentScheduleList = paymentScheduleService.findByCreditOfferOrderByDate(creditOffer);
        model.addAttribute("creditOffer", creditOffer);
        model.addAttribute("paymentScheduleList", paymentScheduleList);
        return "payment-schedule/payment-schedule";
    }
}
