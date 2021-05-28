package ru.savelchev.bank.service;

import ru.savelchev.bank.model.CreditOffer;
import ru.savelchev.bank.model.PaymentSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PaymentScheduleService {

    List<PaymentSchedule> findAll();

    List<PaymentSchedule> findByCreditOfferOrderByDate(CreditOffer creditOffer);

    List<PaymentSchedule> calculatePaymentSchedule(CreditOffer creditOffer, LocalDate date);

    PaymentSchedule findById(UUID id);

    void save(PaymentSchedule paymentSchedule);

    void saveAll(List<PaymentSchedule> paymentScheduleList);

    void deleteById(UUID id);

}
