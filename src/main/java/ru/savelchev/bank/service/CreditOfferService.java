package ru.savelchev.bank.service;

import ru.savelchev.bank.model.CreditOffer;

import java.util.List;
import java.util.UUID;

public interface CreditOfferService {

    List<CreditOffer> findAll();

    CreditOffer findById(UUID id);

    void save(CreditOffer creditOffer);

    void deleteById(UUID id);
}
