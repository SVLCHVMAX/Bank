package ru.savelchev.bank.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savelchev.bank.repository.ClientRepository;
import ru.savelchev.bank.model.Client;
import ru.savelchev.bank.service.ClientService;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteById(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findByLastNameIgnoreCase(String searchName) {
        return clientRepository.findByLastNameIgnoreCase(searchName);
    }

}
