package ru.savelchev.bank.service;

import ru.savelchev.bank.model.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<Client> findAll();

    Client findById(UUID id);

    void save(Client client);

    void deleteById(UUID id);

    List<Client> findByLastNameIgnoreCase(String searchName);
}
