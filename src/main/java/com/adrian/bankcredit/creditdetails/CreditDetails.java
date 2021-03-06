package com.adrian.bankcredit.creditdetails;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.CreditCardNumber;

import com.adrian.bankcredit.consumer.Consumer;
import com.adrian.bankcredit.credit.Credit;
import com.adrian.bankcredit.creditcard.CreditCard;
import com.adrian.bankcredit.installment.Installment;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CreditDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NotNull(message = "LoanAmount cannot be null")
	@Min(value=1000, message="LoanAmount must be more than 1,000")
	@Max(value=100000000, message="LoanAmount must be less than 100,000,000")
	Long loanAmount;
	
	@NotNull(message = "LoanStart cannot be null")
	@Temporal(TemporalType.TIMESTAMP)
	Date loanStart;

	@Min(value=1, message="Months must be more than 0")
	@Max(value=720, message="Months must be less than 721")
	int months;
	
	@NotNull(message = "Consumer cannot be null")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumerId", nullable = false)
    Consumer consumer;
	
	@NotNull(message = "Credit cannot be null")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creditId", nullable = false)
    Credit credit;
	
	@OneToMany(mappedBy = "creditDetails", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	@JsonIgnore
	Set<Installment> installment;
	
	@NotNull(message = "CreditCard cannot be null")
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditCardId", referencedColumnName = "id")
	CreditCard creditCard;
	
	
	public CreditDetails() {}


	public CreditDetails(Long id,
			@NotNull(message = "LoanAmount cannot be null") @Min(value = 1000, message = "LoanAmount must be more than 1,000") @Max(value = 100000000, message = "LoanAmount must be less than 100,000,000") Long loanAmount,
			@NotNull(message = "LoanStart cannot be null") Date loanStart,
			@Min(value = 1, message = "Months must be more than 0") @Max(value = 720, message = "Months must be less than 721") int months,
			@NotNull(message = "Consumer cannot be null") Consumer consumer,
			@NotNull(message = "Credit cannot be null") Credit credit, CreditCard creditCard) {
		super();
		this.id = id;
		this.loanAmount = loanAmount;
		this.loanStart = loanStart;
		this.months = months;
		this.consumer = consumer;
		this.credit = credit;
		this.creditCard = creditCard;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getLoanAmount() {
		return loanAmount;
	}


	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}


	public Date getLoanStart() {
		return loanStart;
	}


	public void setLoanStart(Date loanStart) {
		this.loanStart = loanStart;
	}


	public int getMonths() {
		return months;
	}


	public void setMonths(int months) {
		this.months = months;
	}


	public Consumer getConsumer() {
		return consumer;
	}


	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}


	public Credit getCredit() {
		return credit;
	}


	public void setCredit(Credit credit) {
		this.credit = credit;
	}


	public CreditCard getCreditCard() {
		return creditCard;
	}


	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	public Set<Installment> getInstallment() {
		return installment;
	}


	public void setInstallment(Set<Installment> installment) {
		this.installment = installment;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consumer == null) ? 0 : consumer.hashCode());
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((installment == null) ? 0 : installment.hashCode());
		result = prime * result + ((loanAmount == null) ? 0 : loanAmount.hashCode());
		result = prime * result + ((loanStart == null) ? 0 : loanStart.hashCode());
		result = prime * result + months;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditDetails other = (CreditDetails) obj;
		if (consumer == null) {
			if (other.consumer != null)
				return false;
		} else if (!consumer.equals(other.consumer))
			return false;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (installment == null) {
			if (other.installment != null)
				return false;
		} else if (!installment.equals(other.installment))
			return false;
		if (loanAmount == null) {
			if (other.loanAmount != null)
				return false;
		} else if (!loanAmount.equals(other.loanAmount))
			return false;
		if (loanStart == null) {
			if (other.loanStart != null)
				return false;
		} else if (!loanStart.equals(other.loanStart))
			return false;
		if (months != other.months)
			return false;
		return true;
	}



}
