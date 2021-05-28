package ru.savelchev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelchev.bank.model.Credit;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
    List<Credit> findByName(String searchName);
}
