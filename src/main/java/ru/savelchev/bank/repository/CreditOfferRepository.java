package ru.savelchev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelchev.bank.model.CreditOffer;

import java.util.UUID;

public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {
}
