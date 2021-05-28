package ru.savelchev.bank.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Length(min = 2, max = 30)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 30)
    @Column(name = "middle_name")
    private String middleName;

    @NotBlank
    @Length(min = 2, max = 30)
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Введите номер телефона")
    @Positive
    @Column(name = "tel_number")
    private long telNumber;

    @NotBlank(message = "Введите электронную почту")
    @Email
    @Length(min = 2, max = 255)
    @Column(name = "email")
    private String email;

    @NotNull(message = "Введите номер паспорта")
    @Positive
    @Column(name = "passport")
    private long passport;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CreditOffer> creditOfferList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Bank> banks;

    public Client() {
    }

    public Client(String firstName, String middleName, String lastName, long telNumber, String email, long passport) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.email = email;
        this.passport = passport;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(long telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
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

        Client client = (Client) o;

        if (telNumber != client.telNumber) return false;
        if (passport != client.passport) return false;
        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (middleName != null ? !middleName.equals(client.middleName) : client.middleName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (creditOfferList != null ? !creditOfferList.equals(client.creditOfferList) : client.creditOfferList != null)
            return false;
        return banks != null ? banks.equals(client.banks) : client.banks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (int) (telNumber ^ (telNumber >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (passport ^ (passport >>> 32));
        result = 31 * result + (creditOfferList != null ? creditOfferList.hashCode() : 0);
        result = 31 * result + (banks != null ? banks.hashCode() : 0);
        return result;
    }
}
