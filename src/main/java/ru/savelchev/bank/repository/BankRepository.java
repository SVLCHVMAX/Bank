package ru.savelchev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelchev.bank.model.Bank;

import java.util.List;
import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {

    List<Bank> findByClientLastNameIgnoreCase(String searchName);
}
