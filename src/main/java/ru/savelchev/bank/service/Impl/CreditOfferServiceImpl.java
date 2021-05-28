package ru.savelchev.bank.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savelchev.bank.repository.CreditOfferRepository;
import ru.savelchev.bank.model.CreditOffer;
import ru.savelchev.bank.service.BankService;
import ru.savelchev.bank.service.ClientService;
import ru.savelchev.bank.service.CreditOfferService;
import ru.savelchev.bank.service.CreditService;

import java.util.List;
import java.util.UUID;

@Service
public class CreditOfferServiceImpl implements CreditOfferService {

    private final CreditOfferRepository creditOfferRepository;
    private final ClientService clientService;
    private final CreditService creditService;
    private final BankService bankService;

    @Autowired
    public CreditOfferServiceImpl(CreditOfferRepository creditOfferRepository, ClientService clientService, CreditService creditService, BankService bankService) {
        this.creditOfferRepository = creditOfferRepository;
        this.clientService = clientService;
        this.creditService = creditService;
        this.bankService = bankService;
    }

    @Override
    public List<CreditOffer> findAll() {
        return creditOfferRepository.findAll();
    }

    @Override
    public CreditOffer findById(UUID id) {
        return creditOfferRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

    @Override
    public void deleteById(UUID id) {
        creditOfferRepository.deleteById(id);
    }
}
