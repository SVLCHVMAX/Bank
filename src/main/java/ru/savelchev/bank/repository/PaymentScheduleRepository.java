package ru.savelchev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelchev.bank.model.CreditOffer;
import ru.savelchev.bank.model.PaymentSchedule;

import java.util.List;
import java.util.UUID;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, UUID> {
    List<PaymentSchedule> findByCreditOfferOrderByDate(CreditOffer creditOffer);
}
