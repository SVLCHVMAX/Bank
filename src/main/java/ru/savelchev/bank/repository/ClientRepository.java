package ru.savelchev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.savelchev.bank.model.Client;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByLastNameIgnoreCase(String searchName);
}
