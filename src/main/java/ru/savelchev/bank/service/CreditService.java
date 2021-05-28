package ru.savelchev.bank.service;

import ru.savelchev.bank.model.Credit;

import java.util.List;
import java.util.UUID;

public interface CreditService {

    List<Credit> findAll();

    Credit findById(UUID id);

    void save(Credit credit);

    void deleteById(UUID id);

    List<Credit> findByName(String searchName);
}
