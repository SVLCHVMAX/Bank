package ru.savelchev.bank.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savelchev.bank.repository.CreditRepository;
import ru.savelchev.bank.model.Credit;
import ru.savelchev.bank.service.CreditService;

import java.util.List;
import java.util.UUID;

@Service
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit findById(UUID id) {
        return creditRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Credit credit) {
        creditRepository.save(credit);
    }

    @Override
    public void deleteById(UUID id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<Credit> findByName(String searchName) {
        return creditRepository.findByName(searchName);
    }
}
