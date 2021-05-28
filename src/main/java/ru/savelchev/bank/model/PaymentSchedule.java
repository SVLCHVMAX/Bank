package ru.savelchev.bank.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "payment_schedule")
public class PaymentSchedule {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount_payment")
    private double amountPayment;

    @Column(name = "amount_repayment_body")
    private double amountRepaymentBody;

    @Column(name = "amount_repayment_interest")
    private double amountRepaymentInterest;

    @ManyToOne
    @JoinColumn(name = "credit_offer_id")
    private CreditOffer creditOffer;

    public PaymentSchedule() {
    }

    public PaymentSchedule(Date date, double amountPayment, double amountRepaymentBody, double amountRepaymentInterest, CreditOffer creditOffer) {
        this.date = date;
        this.amountPayment = amountPayment;
        this.amountRepaymentBody = amountRepaymentBody;
        this.amountRepaymentInterest = amountRepaymentInterest;
        this.creditOffer = creditOffer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(double amountPayment) {
        this.amountPayment = amountPayment;
    }

    public double getAmountRepaymentBody() {
        return amountRepaymentBody;
    }

    public void setAmountRepaymentBody(double amountRepaymentBody) {
        this.amountRepaymentBody = amountRepaymentBody;
    }

    public double getAmountRepaymentInterest() {
        return amountRepaymentInterest;
    }

    public void setAmountRepaymentInterest(double amountRepaymentInterest) {
        this.amountRepaymentInterest = amountRepaymentInterest;
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

        PaymentSchedule that = (PaymentSchedule) o;

        if (Double.compare(that.amountPayment, amountPayment) != 0) return false;
        if (Double.compare(that.amountRepaymentBody, amountRepaymentBody) != 0) return false;
        if (Double.compare(that.amountRepaymentInterest, amountRepaymentInterest) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return creditOffer != null ? creditOffer.equals(that.creditOffer) : that.creditOffer == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(amountPayment);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(amountRepaymentBody);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(amountRepaymentInterest);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (creditOffer != null ? creditOffer.hashCode() : 0);
        return result;
    }
}
