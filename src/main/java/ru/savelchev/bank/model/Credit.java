package ru.savelchev.bank.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Length(min = 2, max = 100)
    @Column(name = "name")
    private String name;

    @NotNull(message = "Введите лимит по кредиту")
    @Positive
    @Column(name = "credit_limit")
    private long creditLimit;

    @NotNull(message = "Введите процентную ставку")
    @Positive
    @Column(name = "interest_rate")
    private float interestRate;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<CreditOffer> creditOfferList;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Bank> banks;

    public Credit() {
    }

    public Credit(String name, long creditLimit, float interestRate) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreditOffer> getCreditOfferList() {
        return creditOfferList;
    }

    public void setCreditOfferList(List<CreditOffer> creditOfferList) {
        this.creditOfferList = creditOfferList;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit credit = (Credit) o;

        if (creditLimit != credit.creditLimit) return false;
        if (Float.compare(credit.interestRate, interestRate) != 0) return false;
        if (id != null ? !id.equals(credit.id) : credit.id != null) return false;
        if (name != null ? !name.equals(credit.name) : credit.name != null) return false;
        if (creditOfferList != null ? !creditOfferList.equals(credit.creditOfferList) : credit.creditOfferList != null)
            return false;
        return banks != null ? banks.equals(credit.banks) : credit.banks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (creditLimit ^ (creditLimit >>> 32));
        result = 31 * result + (interestRate != +0.0f ? Float.floatToIntBits(interestRate) : 0);
        result = 31 * result + (creditOfferList != null ? creditOfferList.hashCode() : 0);
        result = 31 * result + (banks != null ? banks.hashCode() : 0);
        return result;
    }
}
