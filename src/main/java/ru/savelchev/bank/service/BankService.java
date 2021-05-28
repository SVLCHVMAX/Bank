package ru.savelchev.bank.service;

import ru.savelchev.bank.model.Bank;

import java.util.List;
import java.util.UUID;

public interface BankService {
    List<Bank> findAll();

    Bank findById(UUID id);

    void save(Bank bank);

    void deleteById(UUID id);

    List<Bank> findByClientLastNameIgnoreCase(String searchName);
}
