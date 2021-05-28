package ru.savelchev.bank.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savelchev.bank.repository.BankRepository;
import ru.savelchev.bank.model.Bank;
import ru.savelchev.bank.service.BankService;

import java.util.List;
import java.util.UUID;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public Bank findById(UUID id) {
        return bankRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public void deleteById(UUID id) {
        bankRepository.deleteById(id);
    }

    @Override
    public List<Bank> findByClientLastNameIgnoreCase(String searchName) {
        return bankRepository.findByClientLastNameIgnoreCase(searchName);
    }
}
