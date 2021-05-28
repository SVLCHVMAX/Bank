package ru.savelchev.bank.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credit_offer")
public class CreditOffer {

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

    @NotNull
    @Positive
    @Column(name = "credit_amount")
    private long creditAmount;

    @NotNull
    @Positive
    @Column(name = "credit_length")
    private int creditLength;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.ALL)
    private List<PaymentSchedule> paymentScheduleList;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.ALL)
    private List<Bank> banks;

    public CreditOffer() {
    }

    public CreditOffer(Client client, Credit credit, long creditAmount, int creditLength) {
        this.client = client;
        this.credit = credit;
        this.creditAmount = creditAmount;
        this.creditLength = creditLength;
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

    public long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getCreditLength() {
        return creditLength;
    }

    public void setCreditLength(int creditLength) {
        this.creditLength = creditLength;
    }

    public List<PaymentSchedule> getPaymentScheduleList() {
        return paymentScheduleList;
    }

    public void setPaymentScheduleList(List<PaymentSchedule> paymentScheduleList) {
        this.paymentScheduleList = paymentScheduleList;
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

        CreditOffer that = (CreditOffer) o;

        if (creditAmount != that.creditAmount) return false;
        if (creditLength != that.creditLength) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;
        if (paymentScheduleList != null ? !paymentScheduleList.equals(that.paymentScheduleList) : that.paymentScheduleList != null)
            return false;
        return banks != null ? banks.equals(that.banks) : that.banks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (int) (creditAmount ^ (creditAmount >>> 32));
        result = 31 * result + creditLength;
        result = 31 * result + (paymentScheduleList != null ? paymentScheduleList.hashCode() : 0);
        result = 31 * result + (banks != null ? banks.hashCode() : 0);
        return result;
    }
}
