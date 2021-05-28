package ru.savelchev.bank.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savelchev.bank.repository.PaymentScheduleRepository;
import ru.savelchev.bank.model.CreditOffer;
import ru.savelchev.bank.model.PaymentSchedule;
import ru.savelchev.bank.service.PaymentScheduleService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentScheduleServiceImpl implements PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    @Autowired
    public PaymentScheduleServiceImpl(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }

    @Override
    public List<PaymentSchedule> findAll() {
        return paymentScheduleRepository.findAll();
    }

    @Override
    public List<PaymentSchedule> findByCreditOfferOrderByDate(CreditOffer creditOffer) {
        return paymentScheduleRepository.findByCreditOfferOrderByDate(creditOffer);
    }

    @Override
    public List<PaymentSchedule> calculatePaymentSchedule(CreditOffer creditOffer, LocalDate date) {
        double interestRate = creditOffer.getCredit().getInterestRate();
        double interestRatePerMonth = interestRate / 100 / 12;
        long creditAmount = creditOffer.getCreditAmount();
        int creditLength = creditOffer.getCreditLength();
        double amountPayment = creditAmount * (interestRatePerMonth + interestRatePerMonth / ((Math.pow(1 + interestRatePerMonth, creditLength)) - 1));
        double scale = Math.pow(10, 2);
        amountPayment = Math.ceil(amountPayment * scale) / scale;
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();
        double amountRepaymentBody;
        double amountRepaymentInterest;
        for (int i = 0; i < creditLength; i++) {
            date = date.plusMonths(1);
            amountRepaymentInterest = creditAmount * (interestRate / 100) * date.lengthOfMonth() / date.lengthOfYear();
            amountRepaymentInterest = Math.ceil(amountRepaymentInterest * scale) / scale;
            amountRepaymentBody = amountPayment - amountRepaymentInterest;
            amountRepaymentBody = Math.ceil(amountRepaymentBody * scale) / scale;
            creditAmount -= amountRepaymentBody;
            PaymentSchedule paymentSchedule = new PaymentSchedule(Date.valueOf(date), amountPayment, amountRepaymentBody, amountRepaymentInterest, creditOffer);
            paymentScheduleList.add(paymentSchedule);
        }
        return paymentScheduleList;
    }

    @Override
    public PaymentSchedule findById(UUID id) {
        return paymentScheduleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(PaymentSchedule paymentSchedule) {
        paymentScheduleRepository.save(paymentSchedule);
    }

    @Override
    public void saveAll(List<PaymentSchedule> paymentScheduleList) {
        paymentScheduleRepository.saveAll(paymentScheduleList);
    }

    @Override
    public void deleteById(UUID id) {
        paymentScheduleRepository.deleteById(id);
    }
}
