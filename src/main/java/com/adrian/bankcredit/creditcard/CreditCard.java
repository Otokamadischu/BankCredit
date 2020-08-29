package com.adrian.bankcredit.creditcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.adrian.bankcredit.creditdetails.CreditDetails;

import org.hibernate.validator.constraints.CreditCardNumber;

@Entity
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NotNull(message = "CreditCardNumber cannot be null")
	@CreditCardNumber(message = "CreditAccountNumber is wrong")
	String creditAccountNumber;
	
	@Column(precision=11, scale=2)
	@Min(value=0, message="Amount must be positive")
	@Max(value=100000000, message="Amount must be less than 100,000,000")
	double amount;
	
	@NotNull(message = "CreditDetails cannot be null")
    @OneToOne(mappedBy = "creditCard")
    CreditDetails creditDetails;
	
	public CreditCard() {
		
	}

	public CreditCard(Long id,
			@NotNull(message = "CreditCardNumber cannot be null") @CreditCardNumber(message = "CreditAccountNumber is wrong") String creditAccountNumber,
			@Min(value = 0, message = "Amount must be positive") @Max(value = 100000000, message = "Amount must be less than 100,000,000") double amount,
			@NotNull(message = "CreditDetails cannot be null") CreditDetails creditDetails) {
		super();
		this.id = id;
		this.creditAccountNumber = creditAccountNumber;
		this.amount = amount;
		this.creditDetails = creditDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreditAccountNumber() {
		return creditAccountNumber;
	}

	public void setCreditAccountNumber(String creditAccountNumber) {
		this.creditAccountNumber = creditAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CreditDetails getCreditDetails() {
		return creditDetails;
	}

	public void setCreditDetails(CreditDetails creditDetails) {
		this.creditDetails = creditDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((creditAccountNumber == null) ? 0 : creditAccountNumber.hashCode());
		result = prime * result + ((creditDetails == null) ? 0 : creditDetails.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CreditCard other = (CreditCard) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (creditAccountNumber == null) {
			if (other.creditAccountNumber != null)
				return false;
		} else if (!creditAccountNumber.equals(other.creditAccountNumber))
			return false;
		if (creditDetails == null) {
			if (other.creditDetails != null)
				return false;
		} else if (!creditDetails.equals(other.creditDetails))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
