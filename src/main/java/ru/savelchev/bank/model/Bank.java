package ru.savelchev.bank.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @ManyToOne
    @JoinColumn(name = "credit_offer_id")
    private CreditOffer creditOffer;

    public Bank() {
    }

    public Bank(Client client, Credit credit, CreditOffer creditOffer) {
        this.client = client;
        this.credit = credit;
        this.creditOffer = creditOffer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public CreditOffer getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (id != null ? !id.equals(bank.id) : bank.id != null) return false;
        if (client != null ? !client.equals(bank.client) : bank.client != null) return false;
        if (credit != null ? !credit.equals(bank.credit) : bank.credit != null) return false;
        return creditOffer != null ? creditOffer.equals(bank.creditOffer) : bank.creditOffer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (creditOffer != null ? creditOffer.hashCode() : 0);
        return result;
    }
}
